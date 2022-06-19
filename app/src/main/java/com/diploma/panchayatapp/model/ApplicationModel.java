package com.diploma.panchayatapp.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Andria on 4/25/2022.
 */

@Entity(tableName = "table_application_form")
public class ApplicationModel {
    @PrimaryKey(autoGenerate = true)
    public int applnID;
    public long mobNum;
    public String panchayatName;
    public String fullname;
    public String profImg;
    public String dob;
    public String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getApplnID() {
        return applnID;
    }

    public void setApplnID(int applnID) {
        this.applnID = applnID;
    }

    public long getMobNum() {
        return mobNum;
    }

    public void setMobNum(long mobNum) {
        this.mobNum = mobNum;
    }

    public String getPanchayatName() {
        return panchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        this.panchayatName = panchayatName;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfImg() {
        return profImg;
    }

    public void setProfImg(String profImg) {
        this.profImg = profImg;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
