package controllers;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rest.UserService;

import java.io.IOException;

public class LoginControl  {

    @FXML
    TextField userEmail;
    @FXML
    TextField userPassword;

    public static User userInfo;

    UserService userService = new UserService();

    @FXML
    public void pressSignUp(MouseEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/signUp.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logIn(ActionEvent add) throws IOException {
        User user = null;
        if(userEmail.getText()!=null&&userPassword.getText()!=null) {
            user = userService.getLogin(userEmail.getText(), userPassword.getText());
        }
        if(user == null)
            ;//Return message to user
        else {
            userInfo = user;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/profile.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(add.getSource())).getScene().getWindow().hide();
        }
    }

}
