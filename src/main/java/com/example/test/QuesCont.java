package com.example.test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.sql.*;
public class QuesCont {
    Main m1 = new Main();

    @FXML
    private Button submit, home;

    @FXML
    private Label q1, q2, q3, q4, q5, q6, q7, q8, q9, q10;

    @FXML
    private RadioButton a1, b1, c1, d1;

    @FXML
    private RadioButton a2, b2, c2, d2;

    @FXML
    private RadioButton a3, b3, c3, d3;

    @FXML
    private RadioButton a4, b4, c4, d4;

    @FXML
    private RadioButton a5, b5, c5, d5;

    @FXML
    private RadioButton a6, b6, c6, d6;

    @FXML
    private RadioButton a7, b7, c7, d7;

    @FXML
    private RadioButton a8, b8, c8, d8;

    @FXML
    private RadioButton a9, b9, c9, d9;

    @FXML
    private RadioButton a10, b10, c10, d10;

    private static Users u1;
    public void toHome() throws IOException {
        m1.sceneChange("MAINPAGE.fxml");
    }

    public void setUser(Users currentUser){
        u1 = currentUser;
    }
    public void fill() throws SQLException {
        DatabaseConnection dbc = new DatabaseConnection();
        Connection conn = dbc.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT distinct q.questionText, q.a1, q.a2, q.a3, q.a4 " +
                "FROM Questions q " + "INNER JOIN Questions_UserGroups qg ON q.qID = qg.qID " +
                "WHERE qg.ugroupID =" +u1.getUgroID()+" " + "ORDER BY RAND() " + "LIMIT 10");

        for (int i = 1; i <= 10; i++) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                switch (i) {
                    case 1:
                        q1.setText(rs.getString("questionText"));
                        a1.setText(rs.getString("a1"));
                        b1.setText(rs.getString("a2"));
                        c1.setText(rs.getString("a3"));
                        d1.setText(rs.getString("a4"));
                        break;
                    case 2:
                        q2.setText(rs.getString("questionText"));
                        a2.setText(rs.getString("a1"));
                        b2.setText(rs.getString("a2"));
                        c2.setText(rs.getString("a3"));
                        d2.setText(rs.getString("a4"));
                        break;
                    case 3:
                        q3.setText(rs.getString("questionText"));
                        a3.setText(rs.getString("a1"));
                        b3.setText(rs.getString("a2"));
                        c3.setText(rs.getString("a3"));
                        d3.setText(rs.getString("a4"));
                        break;
                    case 4:
                        q4.setText(rs.getString("questionText"));
                        a4.setText(rs.getString("a1"));
                        b4.setText(rs.getString("a2"));
                        c4.setText(rs.getString("a3"));
                        d4.setText(rs.getString("a4"));
                        break;
                    case 5:
                        q5.setText(rs.getString("questionText"));
                        a5.setText(rs.getString("a1"));
                        b5.setText(rs.getString("a2"));
                        c5.setText(rs.getString("a3"));
                        d5.setText(rs.getString("a4"));
                        break;
                    case 6:
                        q6.setText(rs.getString("questionText"));
                        a6.setText(rs.getString("a1"));
                        b6.setText(rs.getString("a2"));
                        c6.setText(rs.getString("a3"));
                        d6.setText(rs.getString("a4"));
                        break;
                    case 7:
                        q7.setText(rs.getString("questionText"));
                        a7.setText(rs.getString("a1"));
                        b7.setText(rs.getString("a2"));
                        c7.setText(rs.getString("a3"));
                        d7.setText(rs.getString("a4"));
                        break;
                    case 8:
                        q8.setText(rs.getString("questionText"));
                        a8.setText(rs.getString("a1"));
                        b8.setText(rs.getString("a2"));
                        c8.setText(rs.getString("a3"));
                        d8.setText(rs.getString("a4"));
                        break;
                    case 9:
                        q9.setText(rs.getString("questionText"));
                        a9.setText(rs.getString("a1"));
                        b9.setText(rs.getString("a2"));
                        c9.setText(rs.getString("a3"));
                        d9.setText(rs.getString("a4"));
                        break;
                    case 10:
                        q10.setText(rs.getString("questionText"));
                        a10.setText(rs.getString("a1"));
                        b10.setText(rs.getString("a2"));
                        c10.setText(rs.getString("a3"));
                        d10.setText(rs.getString("a4"));
                }
            }
        }
        dbc.closeConnection();
    }

    @FXML
    private void whenSubmitted() throws IOException, SQLException{

        DatabaseConnection dbc = new DatabaseConnection();
        Connection conn = dbc.connect();

        int total = 0;
        int mood = 0;

        RadioButton[] arr1 = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10};
        RadioButton[] arr2 = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10};
        RadioButton[] arr3 = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        //RadioButton [] arr4={d1,d2,d3,d4,d5,d6,d7,d8,d9,d10};

        for (int i = 0; i < 10; i++) {
            if (arr1[i].isSelected()) {
                total = total + 3;
            } else if (arr2[i].isSelected()) {
                total = total + 2;
            } else if (arr3[i].isSelected()) {
                total = total + 1;
            } //else if (arr4[i].isSelected()) {
                //total = total + 0;

            //}
        }

        if(total <= 6 ){
            mood = 1;
        }else if(total >= 7 && total <= 12){
            mood = 2;
        }else if(total >= 13 && total <= 18){
            mood = 3;
        }else if(total >= 19 && total <= 24){
            mood = 4;
        }else if(total >= 25 && total <= 30){
            mood = 5;
        }

        Statement state = conn.createStatement();
        state.executeUpdate("INSERT INTO questionnaire values (Default, NOW(), " + mood + ", " + u1.getuID() + ")");


        MoodCont moodP = new MoodCont();
        u1.setCurrentMood(mood);
        moodP.setUser(u1);

        m1.sceneChange("Mood.fxml");
    }
    public void initialize () throws SQLException {
        fill();
    }
}
