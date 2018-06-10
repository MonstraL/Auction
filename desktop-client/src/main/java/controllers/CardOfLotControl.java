package controllers;

import entity.Auction;
import entity.AuctionStatus;
import entity.Lot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rest.LotService;
import rest.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardOfLotControl implements Initializable {

    protected Lot lot = LotsListControl.tempLot;

    private LotService lotService = new LotService();
    private UserService userService = new UserService();

    @FXML
    Label lotName;
    @FXML
    TextArea lotDescription;
    @FXML
    TextField lotLocation;
    @FXML
    TextField startPrice;

    public void removeLot(ActionEvent remove) {
        lotService.deleteLot(lot.getId());
    }

    public void bidsLot(ActionEvent bids) throws IOException {
        Lot initializedLot = lotService.getLot(lot.getId());
        BidsListControl.bidsData = initializedLot.getBids();
        BidsListControl.auctionOfBids = initializedLot.getAuction();
        BidsListControl.lot = initializedLot;
        System.out.println(BidsListControl.lot.getId());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/bids.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
       // stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node) (bids.getSource())).getScene().getWindow().hide();
    }

    public void openTrader(ActionEvent open) throws IOException {
        TraderProfileControl.user = lotService.getLot(lot.getId()).getSeller();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/traderProfile.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lotName.setText(lot.getName());
        lotDescription.setText(lot.getDescription());
        lotLocation.setText(lot.getLocation());
        startPrice.setText(String.valueOf(lot.getPrice()));
    }
}
