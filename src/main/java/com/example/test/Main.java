package com.example.test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;
    @Override
    public void start(Stage mainStage) throws IOException {
        stage = mainStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        mainStage.setTitle("Mood Monitor");
        mainStage.setScene(new Scene(root, 600, 400));
        mainStage.show();
    }
    public static void sceneChange(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxml));
        stage.getScene().setRoot(root);
    }
    public static void main(String[] args) {
        launch();
    }
}
