package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rest.UserService;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/login.fxml"));
       // primaryStage.initStyle(StageStyle.UNDECORATED); //title bar off
        primaryStage.setTitle("Online Auction");
        primaryStage.setScene(new Scene(root, 300, 120));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
