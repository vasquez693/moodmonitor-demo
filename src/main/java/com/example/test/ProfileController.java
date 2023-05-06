package com.example.test;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.*;

public class ProfileController {

    @FXML
    private Button home, update;
    @FXML
    private TextField nameTxt, bioTxt, genderTxt, userNTxt;
    @FXML
    private PasswordField passTxt;
    @FXML
    private DatePicker dob;
    @FXML
    private ChoiceBox education, empSta;

    private static Users u1;


    @FXML
    private void returnHome() throws IOException {
        Main m1 = new Main();
        m1.sceneChange("Mainpage.fxml");
    }

    public void fillInfo(Users currentUser){

        u1 = currentUser;

    }
    @FXML
    private void updateInfo() throws SQLException{

        DatabaseConnection c1 = new DatabaseConnection();
        Connection connect = c1.connect();
        Statement statement = connect.createStatement();

        if(nameTxt.getText().compareTo(u1.getName()) != 0){
            statement.executeUpdate("UPDATE user SET name = '"+nameTxt.getText()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setName(nameTxt.getText());
        }
        if(passTxt.getText().compareTo((u1.getPassword())) != 0){
            statement.executeUpdate("UPDATE user SET password = '"+passTxt.getText()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setPassword(passTxt.getText());
        }
        if(bioTxt.getText().compareTo(u1.getBio()) != 0){
            statement.executeUpdate("UPDATE user SET bio = '"+bioTxt.getText()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setBio(bioTxt.getText());
        }
        if(genderTxt.getText().compareTo(u1.getGender()) != 0){
            statement.executeUpdate("UPDATE user SET gender = '"+genderTxt.getText()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setGender(genderTxt.getText());
        }
        if(userNTxt.getText().compareTo(u1.getUsername()) != 0){
            statement.executeUpdate("UPDATE user SET userName = '"+userNTxt.getText()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setUserName(userNTxt.getText());
        }
        if(dob.getValue() != u1.getDob().toLocalDate()){
            statement.executeUpdate("UPDATE user SET DOB = '"+dob.getValue()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setDOB(Date.valueOf(dob.getValue()));
        }
        if(education.getValue() != u1.getEducation()){
            statement.executeUpdate("UPDATE user SET education = '"+education.getValue()+"' WHERE userID = " +u1.getuID()+ "");
            u1.setEdu(education.getValue().toString());
        }
        if(empSta.getValue() != u1.getEmpSta()) {
            statement.executeUpdate("UPDATE user SET employmentStatus = '"+empSta.getValue().toString()+"' WHERE userID = " + u1.getuID() + "");
            u1.setEmpSta(empSta.getValue().toString());
        }

        c1.closeConnection();

    }
    public void initialize(){

        nameTxt.setText(u1.getName());
        passTxt.setText(u1.getPassword());
        bioTxt.setText(u1.getBio());
        genderTxt.setText(u1.getGender());
        userNTxt.setText(u1.getUsername());

        dob.setValue(u1.getDob().toLocalDate());

        education.getItems().addAll("In School", "High School", "Some College", "Associates", "Bachelors", "Masters", "PHD", "Other");

        empSta.getItems().addAll("Have a Job", "Currently Not Working");
        empSta.setValue(u1.getEmpSta());

        education.setValue(u1.getEducation());

        if(u1.getEmpSta().equals("Have a Job")){
            empSta.setValue("Have a Job");
        }else{
            empSta.setValue("Currently Not Working");
        }
    }
}

