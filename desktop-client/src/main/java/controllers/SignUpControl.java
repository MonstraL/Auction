package controllers;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rest.UserService;

import java.io.IOException;

public class SignUpControl  {

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

    @FXML
    public void registration(ActionEvent creat) throws IOException {
        UserService userService = new UserService();
        if(pass.getText().equals(rePass.getText())&&emailAdd.getText().contains("@")&&pass.getText().length()>5) {
            User user = new User();
            user.setEmail(emailAdd.getText());
            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setPassword(pass.getText());
            if(!userService.createUser(user))
                ;//Message user about email existence
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/login.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ((Node)(creat.getSource())).getScene().getWindow().hide();
            }
        }
    }

    @FXML
    public void pressClearButt(MouseEvent event)  {
        firstName.clear();
        emailAdd.clear();
        pass.clear();
        rePass.clear();
        lastName.clear();
    }
}
