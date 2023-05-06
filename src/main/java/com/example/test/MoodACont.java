package com.example.test;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;


public class MoodACont {

    @FXML
    private Button home, download;
    @FXML
    private Label mood, a1, a2, a3, a4, ad1, ad2, ad3, ad4;
    Main m1 = new Main();

    private static Users u1;

    public void setUser(Users currentUser){
        u1 = currentUser;
    }

    @FXML
    public void retrieveMoods()throws SQLException{

        DatabaseConnection dbc = new DatabaseConnection();
        Connection conn = dbc.connect();
        Statement state = conn.createStatement();

        ResultSet rs = state.executeQuery("SELECT moodName FROM mood WHERE moodID ='"+u1.getMood()+"'");

        if(rs.next()) {
            mood.setText(rs.getString("moodName"));
        }

        PreparedStatement stmt2 = conn.prepareStatement(
                "SELECT activityName, activityDescription FROM Activity " +
                        "INNER JOIN Mood_Activity ON Activity.activityID = Mood_Activity.activityID " +
                        "WHERE Mood_Activity.moodID = ? " +
                        "ORDER BY RAND() " +
                        "LIMIT 4;");
        stmt2.setInt(1, u1.getMood());

        ResultSet rs2 = stmt2.executeQuery();
        for (int i = 1; i <= 4; i++) {
            if (rs2.next()) {
                switch (i) {
                    case 1:
                        a1.setText(rs2.getString("activityName"));
                        ad1.setText(rs2.getString("activityDescription"));
                        break;
                    case 2:
                        a2.setText(rs2.getString("activityName"));
                        ad2.setText(rs2.getString("activityDescription"));
                        break;
                    case 3:
                        a3.setText(rs2.getString("activityName"));
                        ad3.setText(rs2.getString("activityDescription"));
                        break;
                    case 4:
                        a4.setText(rs2.getString("activityName"));
                        ad4.setText(rs2.getString("activityDescription"));
                        break;
                }
            }

        }
    }

    @FXML
    private void toHome () throws IOException {
        m1.sceneChange("Mainpage.fxml");
    }
    public void initialize() throws SQLException {
        retrieveMoods();

        ad1.setEllipsisString("");
        ad1.setWrapText(true);
        ad2.setEllipsisString("");
        ad2.setWrapText(true);
        ad3.setEllipsisString("");
        ad3.setWrapText(true);
        ad4.setEllipsisString("");
        ad4.setWrapText(true);
    }
}
