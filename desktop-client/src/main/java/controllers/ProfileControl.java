package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileControl extends ControlWithSide implements Initializable {
    @FXML
    JFXTextField firstName;
    @FXML
    JFXTextField lastName;
    @FXML
    JFXTextField email;
    @FXML
    JFXTextField creationDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSide(location, resources);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        creationDate.setText(String.valueOf(user.getCreationTimestamp()));
    }

}