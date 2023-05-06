package com.example.test;

import java.sql.*;

public class Users {

    private int uID, ugroID, mood;
    private String username, password, name, gender, bio, education, employmentStatus;

    private Date dob;

    public Users(int currentUid, int ugroupID, String userName, String passW, String nameOfU, String genderOfU, String bioOfU, String eduOfU, Date dobOfU, String empSta ){

        this.uID = currentUid;
        this.ugroID = ugroupID;
        this.username = userName;
        this.password = passW;
        this.name = nameOfU;
        this.gender = genderOfU;
        this.bio = bioOfU;
        this.dob = dobOfU;
        this.employmentStatus = empSta;
        this.education = eduOfU;

    }

    public void setCurrentMood(int currentMood){
        this.mood = currentMood;
    }
    public int getMood(){
        return mood;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public void setUgroID(int newUGroup){this.ugroID = newUGroup; }
    public void setPassword(String newPass){
        this.password = newPass;
    }
    public void setUserName(String newUserN){
        this.username = newUserN;
    }
    public void setGender(String newGender){
        this.gender = newGender;
    }
    public void setBio(String newBio){
        this.bio = newBio;
    }
    public void setDOB(Date newDate){
        this.dob = Date.valueOf(newDate.toLocalDate());
    }
    public void setEmpSta(String newEmpS){
        this.employmentStatus = newEmpS;
    }
    public void setEdu(String newEdu){
        this.education = newEdu;
    }

    public int getuID(){
        return this.uID;
    }

    public int getUgroID(){ return this.ugroID; }
    public String getEmpSta(){
        return this.employmentStatus;
    }
    public String getEducation(){
        return this.education;
    }
    public String getName(){
        return name;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getGender(){
        return this.gender;
    }

    public String getBio(){
        return this.bio;
    }

    public Date getDob(){
        return dob;
    }

}
