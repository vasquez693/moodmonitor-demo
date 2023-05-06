package com.example.test;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginCont {

    @FXML
    private Button b1, b2;
    @FXML
    private TextField t1;
    @FXML
    private PasswordField p1;

    @FXML
    private void onLoginAttempt() throws IOException, SQLException {

        // Main m1 = new Main();
        Alert  alert = new Alert(Alert.AlertType.WARNING);

        DatabaseConnection c1 = new DatabaseConnection();
        Connection connect = c1.connect();
        Statement state = connect.createStatement();

        String user = t1.getText();
        String pass = p1.getText();

        ResultSet result = state.executeQuery("SELECT * from user where userName = '"+user+"' and binary password = '"+pass+"'");

        if (result.next()) {
            Users currentUser = new Users(
                    result.getInt("userID"),
                    result.getInt("ugroupID"),
                    result.getString("userName"),
                    result.getString("password"),
                    result.getString("name"),
                    result.getString("gender"),
                    result.getString("bio"),
                    result.getString("education"),
                    result.getDate("DOB"),
                    result.getString("employmentStatus")
            );

            ProfileController u = new ProfileController();
            u.fillInfo(currentUser);

            QuesCont qUser = new QuesCont();
            qUser.setUser(currentUser);

            TrendsCont tUser = new TrendsCont();
            tUser.setUser (currentUser);

            MainPageCont mUser = new MainPageCont();
            mUser.setUser(currentUser);

            ResultsCont rUser = new ResultsCont();
            rUser.setUser(currentUser);

            Main.sceneChange("Mainpage.fxml");
        }else{
            alert.setTitle("OOPS");
            alert.setContentText("Incorrect Username/Password");
            alert.show();
        }
        c1.closeConnection();
    }
    @FXML
    private void onCreateAccount() throws IOException {
        Main m2 = new Main();
        m2.sceneChange("CreateAccount.fxml");
    }
}


