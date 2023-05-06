package com.example.test;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.*;

public class TrendsCont {
    Main m1 = new Main();

    @FXML
    private LineChart<String, Number> moodChart;

    @FXML
    private Button home;
    private static Users u1;
    public void setUser(Users currentUser){
        u1 = currentUser;
    }
    public void chartFill() throws SQLException {
        DatabaseConnection dbc = new DatabaseConnection();
        Connection conn = dbc.connect();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Mood ");

        PreparedStatement stmt = conn.prepareStatement("SELECT  distinct q.dateTaken, m.moodID FROM Questionnaire q JOIN Mood m ON q.moodID = m.moodID WHERE q.userID = ? ORDER BY q.dateTaken DESC LIMIT 5");
        stmt.setInt(1, u1.getuID());
        ResultSet rs = stmt.executeQuery();

        moodChart.setTitle("Mood Trends");
        moodChart.setLegendVisible(false);
        moodChart.setCreateSymbols(false);
        moodChart.setAnimated(false);
        moodChart.setHorizontalGridLinesVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        while (rs.next()) {
            String date = rs.getDate("dateTaken").toString();
            int moodID = rs.getInt("moodID");
            series.getData().add(new XYChart.Data<>(date, moodID));
        }

        moodChart.getData().add(series);
        moodChart.setAnimated(true);
        dbc.closeConnection();
    }
    public void initialize() throws SQLException{
        chartFill();
    }
    @FXML
    private void toHome () throws IOException {
        m1.sceneChange("Mainpage.fxml");
    }


}