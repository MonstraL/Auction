package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entity.Auction;
import entity.AuctionStatus;
import entity.Lot;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import rest.AuctionService;
import rest.LotService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LotsListControl extends ControlWithSide implements Initializable {
    @FXML ImageView imageChooseIcon;
    @FXML TextArea newDescription;
    @FXML TextField newLotName;
    @FXML TextField newLotLocation;
    @FXML TextField newMinPrice;
    @FXML VBox lotsVBox;
    @FXML Button addLotButt;

    protected static Auction auctionOfLot;

    protected static Lot tempLot;

    private LotService lotService = new LotService();
    private AuctionService auctionService = new AuctionService();
    protected static List<Lot> lotsData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSide(location, resources);
        try {
            openListOfLots();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openListOfLots() throws IOException {
        Lot lot  = null;
        Iterator<Lot> iterator = lotsData.iterator();
        while(iterator.hasNext())
        {
            lot = iterator.next();
            tempLot = lot;
            lotsVBox.getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("Views/lotCard.fxml")));
        }
    }

    public void addNewLot(ActionEvent add) throws IOException {
        if(auctionOfLot==null||auctionOfLot.getStatus()!= AuctionStatus.PLANNED)
            return;
        Lot lot = new Lot();
        lot.setName(newLotName.getText());
        lot.setSeller(user);
        System.out.println(lot.getSeller().getCreationTimestamp());
        System.out.println(user.getCreationTimestamp());
        lot.setAuction(auctionOfLot);
        lot.setLocation(newLotLocation.getText());
        lot.setDescription(newDescription.getText());
        lot.setPrice(Integer.parseInt(newMinPrice.getText()));
        if (!lotService.createLot(lot))
            System.out.println("Error L");//show error to user
        else {
            tempLot = lot;
            lotsVBox.getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("Views/lotCard.fxml")));
        }
    }

    public void handleLotImage(ActionEvent t) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageChooseIcon.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(LotsListControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Add file to db
    }


}
