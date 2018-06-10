package controllers;

import com.jfoenix.controls.JFXButton;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rest.AuctionService;
import rest.UserService;

import java.io.IOException;

public class SidePanelControl {

    private User user = LoginControl.userInfo;

    private AuctionService auctionService = new AuctionService();

    @FXML
    private void changePage(ActionEvent event) throws IOException {

        JFXButton btn = (JFXButton) event.getSource();
        FXMLLoader fxmlLoader = null;
        switch(btn.getText())
        {
            case "Profile":fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/profile.fxml"));
                break;
            case "Auctions":
                AuctionsListControl.auctionsData = auctionService.getAllAuctions();
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/auctions.fxml"));
                break;
            case "My Auctions":
                AuctionsListControl.auctionsData = user.getAuctions();
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/auctions.fxml"));
                break;
            case "My Lots":
                LotsListControl.auctionOfLot = null;
                LotsListControl.lotsData = user.getLotsForSale();
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/lots.fxml"));
                break;
            case "My Bids":
                BidsListControl.bidsData = user.getBids();
                BidsListControl.auctionOfBids = null;
                fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/bids.fxml"));
                break;
            case "Edit profile":fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/editProfile.fxml"));
                break;
        }
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
}
