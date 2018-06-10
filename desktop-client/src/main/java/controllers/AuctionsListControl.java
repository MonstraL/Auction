package controllers;

import entity.Auction;
import entity.AuctionStatus;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rest.AuctionService;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class AuctionsListControl extends ControlWithSide implements Initializable {

    protected static List<Auction> auctionsData;

    private AuctionService auctionService = new AuctionService();

    @FXML
    Label labelOfAction;
    @FXML
    Button addAuctionButt;
    @FXML
    TableView auctionTable;
    @FXML
    TableColumn nameAuction;
    @FXML
    TableColumn statusAuction;
    @FXML
    TableColumn startTimeAuction;
    @FXML
    TableColumn endTimeAuction;

    @FXML
    TextField newAuctionName;
    @FXML
    DatePicker newStartDate;
    @FXML
    DatePicker newEndDate;
    @FXML
    TextField newStartTime;
    @FXML
    TextField newEndTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSide(location, resources);
        setAuctionsTable();
    }

    public void setAuctionsTable(){
        ObservableList auctions = FXCollections.observableList(auctionsData);
        auctionTable.setItems(auctions);
        nameAuction.setCellValueFactory(new PropertyValueFactory("name"));
        statusAuction.setCellValueFactory(new PropertyValueFactory("status"));
        startTimeAuction.setCellValueFactory(new PropertyValueFactory("startDate"));
        endTimeAuction.setCellValueFactory(new PropertyValueFactory("endDate"));
        auctionTable.getColumns().setAll(nameAuction, statusAuction, startTimeAuction, endTimeAuction);
    }

    public Timestamp stringToTimestapm(String stringDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(stringDate);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        return timestamp;
    }

    public void addNewAuction(ActionEvent add) throws ParseException {
            int lotNumber = auctionTable.getSelectionModel().getSelectedIndex();
        if(addAuctionButt.getText().contains("Add")){
            Auction auction = new Auction();
            auction.setName(newAuctionName.getText());
            auction.setStartDate(stringToTimestapm(String.valueOf(newStartDate.getValue()) + " " + newStartTime.getText()));
            auction.setEndDate(stringToTimestapm(String.valueOf(newEndDate.getValue()) + " " + newEndTime.getText()));
            if (!auctionService.createAuction(auction))
                System.out.println("Error");}
        else
            if (lotNumber < auctionsData.size())
                try {
                    Auction auction = auctionsData.get(lotNumber);
                    if(auction.getStatus()== AuctionStatus.CLOSED)
                        return;
                    auction.setEndDate(stringToTimestapm(String.valueOf(newEndDate.getValue()) + " " + newEndTime.getText()));
                    auction.setName(newAuctionName.getText());
                    auction.setStartDate(stringToTimestapm(String.valueOf(newStartDate.getValue()) + " " + newStartTime.getText()));
                    if (!auctionService.updateAuction(auctionsData.get(lotNumber)))
                        ;//message to user
                }
                finally {
                    labelOfAction.setText("Add new Auction");
                    addAuctionButt.setText("Add");
                }
        auctionsData = auctionService.getAllAuctions();
        for (int i = 0; i < auctionTable.getItems().size(); i++)
            auctionTable.getItems().clear();
        setAuctionsTable();
    }

    public void editAuction(ActionEvent edit)  {
                labelOfAction.setText("Edit an Auction");
                addAuctionButt.setText("Edit");
    }

    public void deleteAuction(ActionEvent delete)  {
        int lotNumber = auctionTable.getSelectionModel().getSelectedIndex();
        if (lotNumber < auctionsData.size()){
            if(!auctionService.deleteAuction(auctionsData.get(lotNumber).getId()))
                ;//message to user
            else{
                auctionsData = auctionService.getAllAuctions();
                for ( int i = 0; i<auctionTable.getItems().size(); i++) {
                    auctionTable.getItems().clear();
                }
                setAuctionsTable();
            }
            //Sys
        }
    }

    public void openLots(ActionEvent openLots) throws IOException {
        int lotNumber = auctionTable.getSelectionModel().getSelectedIndex();
        if (lotNumber < auctionsData.size()) {
            LotsListControl.lotsData = auctionsData.get(lotNumber).getLots();
            LotsListControl.auctionOfLot = auctionsData.get(lotNumber);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/lots.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node) (openLots.getSource())).getScene().getWindow().hide();
        }
    }
}
