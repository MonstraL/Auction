package controllers;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import rest.BidService;
import rest.LotService;
import rest.UserService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BidsListControl extends ControlWithSide implements Initializable {

    @FXML
    TableView tableOfBids;
    @FXML
    TableColumn idOfBidder;
    @FXML
    TableColumn valueOfBid;
    @FXML
    TableColumn dateOfBid;
    @FXML
    TextField newBidValue;

    static List<Bid> bidsData;
    static Auction auctionOfBids;
    static Lot lot;

    private LotService lotService = new LotService();
    private BidService bidService = new BidService();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSide(location, resources);
        setBidsTable();
    }

    public void setBidsTable(){
        ObservableList auctions = FXCollections.observableList(bidsData);
        tableOfBids.setItems(auctions);
        idOfBidder.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        valueOfBid.setCellValueFactory(new PropertyValueFactory("value"));
        dateOfBid.setCellValueFactory(new PropertyValueFactory("timestamp"));
        tableOfBids.getColumns().setAll(idOfBidder, valueOfBid, dateOfBid);
    }

    public void addBid(ActionEvent add) {
        if(auctionOfBids == null||auctionOfBids.getStatus()!=AuctionStatus.OPEN||lot.getSeller().getId()==user.getId())
            return;
        Bid bid = new Bid();
        bid.setBidder(user);
        bid.setLot(lot);
        bid.setValue(Integer.parseInt(newBidValue.getText()));
        if(!bidService.createBidB(bid))
            return;//Message to User
        bidsData = lotService.getLot(lot.getId()).getBids();
        userService.getRegisterForAuction(auctionOfBids.getId(), user.getId());
        for (int i = 0; i < tableOfBids.getItems().size(); i++)
            tableOfBids.getItems().clear();
        setBidsTable();
    }
}

