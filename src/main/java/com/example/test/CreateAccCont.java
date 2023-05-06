package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;


public class CreateAccCont {
    @FXML
    private TextField nameTF, userNameTF, genderTF, bioTF;
    @FXML
    private PasswordField passwordPW;
    @FXML
    private ChoiceBox educationCB, jobStatusCB;
    @FXML
    private DatePicker dobP;
    @FXML
    private Button saveB, back;

    @FXML
    private void onInfoInput() throws IOException, SQLException {

        Main m1 = new Main();

        DatabaseConnection c1 = new DatabaseConnection();
        Connection connect = c1.connect();
        Statement state = connect.createStatement();


        String edu = educationCB.getValue().toString();

        int group = determineUserGroup(dobP.getValue(), edu, jobStatusCB.getValue().toString());

        state.executeUpdate("Insert into user values (DEFAULT , '"+group+"', '" + userNameTF.getText() + "', '" + nameTF.getText() + "', '" + dobP.getValue() + "', '" + genderTF.getText() + "', '" + bioTF.getText() + "', '" + jobStatusCB.getValue().toString() + "', '" + edu + "', '" + passwordPW.getText() + "')");
        m1.sceneChange("Login.fxml");

        c1.closeConnection();
    }

    private int determineUserGroup(LocalDate age, String school, String job ){
        int group = 0;

        LocalDate now = LocalDate.now();
        Period period = Period.between(age, now);
        int ageDiff = period.getYears();

        if(ageDiff <= 25 && school.equals("In School") && job.equals("Have a Job")){
            group = 1;
        }else if(ageDiff <= 25 && school.equals("In School") && job.equals("Currently Not Working")){
            group = 2;
        }else if(ageDiff <= 25 && school.compareTo("In School") < 0 && job.equals("Currently Not Working")){
            group = 3;
        }else if(ageDiff <= 25 && school.compareTo("In School") < 0 && job.equals("Have a Job")){
            group = 4;
        }else if(ageDiff >= 26 && school.equals("In School") && job.equals("Have a Job")){
            group = 5;
        }else if(ageDiff >= 26 && school.equals("In School") && job.equals("Currently Not Working")){
            group = 6;
        }else if(ageDiff >= 26 && school.compareTo("In School") < 0 && job.equals("Currently Not Working")){
            group = 7;
        }else if(ageDiff >= 26 && school.compareTo("In School") < 0 && job.equals("Have a Job")){
            group = 8;
        }
        return group;
    }
    @FXML
    private void returnToLogin() throws IOException{
        Main m2 = new Main();
        m2.sceneChange("Login.fxml");
    }
    @FXML
    private void initialize(){
        educationCB.getItems().addAll("In School", "High School", "Some College", "Associates", "Bachelors", "Masters", "PHD", "Other");
        educationCB.setValue("High School");

        jobStatusCB.getItems().addAll("Have a Job", "Currently Not Working");
        jobStatusCB.setValue("Have a Job");
    }
}