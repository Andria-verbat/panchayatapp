package com.diploma.panchayatapp.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Andria on 4/23/2022.
 */

@Entity(tableName = "table_projecs")
public class ProjectModel {
    @PrimaryKey(autoGenerate = true)
    public int pid;
    public String panchayatName;
    public String projectname;
    public String projectDesc;
    public int duration;
    public String startDate;
    public String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPanchayatName() {
        return panchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        this.panchayatName = panchayatName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
}
