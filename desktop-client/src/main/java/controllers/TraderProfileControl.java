package controllers;

import com.jfoenix.controls.JFXTextField;
import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TraderProfileControl implements Initializable {
    @FXML
    JFXTextField firstName;
    @FXML
    JFXTextField lastName;
    @FXML
    JFXTextField email;
    @FXML
    JFXTextField creationDate;

    static User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        creationDate.setText(String.valueOf(user.getCreationTimestamp()));
    }
}
