package com.example.test;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public class MainPageCont {
    @FXML
    private Button back, results, q1, trends;
    @FXML
    private MenuButton sett;

    public static Users u1;
    Main m = new Main();

    public void setUser(Users currentUser){
        u1 = currentUser;
    }
    @FXML
    private void returnToLogin() throws IOException{
        m.sceneChange("Login.fxml");
    }

    @FXML
    private void toProfile() throws IOException {
        m.sceneChange("Profile.fxml");
    }

    @FXML
    private void toResults() throws IOException{
        m.sceneChange("Results.fxml");
    }

    @FXML
    private void toQuestionnaire() throws IOException, SQLException {

        DatabaseConnection c1 = new DatabaseConnection();
        Connection connect = c1.connect();
        Statement state = connect.createStatement();

        ResultSet rs = state.executeQuery("SELECT dateTaken FROM questionnaire WHERE userID = " + u1.getuID() + " AND DATE(dateTaken) = '" + LocalDate.now() + "'");

        if(rs.next()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("OOPS");
            alert.setContentText("Already took a mood test today! Please return tomorrow to take test again.");
            alert.show();
        }else {
            m.sceneChange("Questionnaire .fxml");
        }
        c1.closeConnection();
    }
    @FXML
    private void toTrends() throws IOException{
        m.sceneChange("Trends.fxml");
    }

    public void initialize(){
        sett.getItems().clear();
        MenuItem profile = new MenuItem("Profile");
        profile.setOnAction(event -> {
            try {
                toProfile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        MenuItem settings = new MenuItem("Settings");

        sett.getItems().addAll(profile, settings);

    }
}
