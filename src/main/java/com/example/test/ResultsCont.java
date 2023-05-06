package com.example.test;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ResultsCont {

    @FXML
    private Button b1, suggestions;
    @FXML
    private TableView moods;
    @FXML
    private ScrollBar scroll;
    public static Users u1;

    Main m1 = new Main();

    public void setUser(Users currentUser){
        u1= currentUser;
    }

    @FXML
    private void returnHome() throws IOException {
        m1.sceneChange("Mainpage.fxml");
    }

    public void tableFill() throws SQLException {

        DatabaseConnection dbc = new DatabaseConnection();
        Connection conn = dbc.connect();
        Statement state = conn.createStatement();

        ResultSet rs = state.executeQuery("SELECT q.dateTaken, m.moodName " +
                "FROM Questionnaire q " +
                "INNER JOIN Mood m ON q.moodID = m.moodID " +
                "WHERE q.userID = " + u1.getuID() + " " +
                "ORDER BY q.dateTaken DESC " +
                "LIMIT 5 ");


        while (rs.next()) {
            LocalDate date = rs.getDate("dateTaken").toLocalDate();
            String mood = rs.getString("moodName");

            Object[] row = {date, mood};
            moods.getItems().add(row);
        }

        TableColumn<Object[], String> descriptionColumn = (TableColumn<Object[], String>) moods.getColumns().get(1);
        TableColumn<Object[], LocalDate> dateColumn = (TableColumn<Object[], LocalDate>) moods.getColumns().get(0);
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1].toString()));
        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(LocalDate.parse(cellData.getValue()[0].toString())));

        dbc.closeConnection();
    }
    @FXML
    private void showMoodAct() throws SQLException, IOException {

        Object[] row = (Object[]) moods.getSelectionModel().getSelectedItem();
        if (row != null) {
            String moodN = (String) row[1];

            DatabaseConnection dbc = new DatabaseConnection();
            Connection conn = dbc.connect();
            Statement state = conn.createStatement();

            ResultSet rs = state.executeQuery("SELECT moodID FROM mood WHERE moodName ='"+moodN+"'");
            if (rs.next()){
                int mood = rs.getInt("moodID");
                MoodACont request = new MoodACont();
                u1.setCurrentMood(mood);
                request.setUser(u1);
                m1.sceneChange("MoodAgain.fxml");
            }
            dbc.closeConnection();
        } else {

        }
    }
    public void initialize() throws SQLException{
        tableFill();

        moods.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setPrefHeight(46);
            return row ;
        });
    }
}
