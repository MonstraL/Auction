package controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rest.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileEditingControl extends ControlWithSide implements Initializable {
    @FXML
    TextField emailAdd;
    @FXML
    TextField pass;
    @FXML
    PasswordField rePass;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;

    UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSide(location, resources);
        emailAdd.setText(user.getEmail());
        pass.setText(user.getPassword());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
    }

    public void pressClearButt(MouseEvent event){
        firstName.clear();
        emailAdd.clear();
        pass.clear();
        rePass.clear();
        lastName.clear();
    }

    public void openLoginPage(MouseEvent someEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node)(someEvent.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void updateProfile(MouseEvent edit) throws IOException {
        if(pass.getText().equals(rePass.getText())&&pass.getText().length()>5)
        {
            user.setLastName(lastName.getText());
            user.setFirstName(firstName.getText());
            user.setEmail(emailAdd.getText());
            user.setPassword(pass.getText());
            if(!userService.updateUser(user))
                ; //email already exist
            else openLoginPage(edit);
            }
        else ;//message to user, wrong email or password
    }


    public void deregistration(MouseEvent delete) throws IOException {
        if(!userService.deleteUser(LoginControl.userInfo.getId()))
            ;//message abou auction in open status
        else openLoginPage(delete);

    }

    public void handleProfileImage(ActionEvent image) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
         //   Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException ex) {
            Logger.getLogger(LotsListControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Add file to db
    }
}
