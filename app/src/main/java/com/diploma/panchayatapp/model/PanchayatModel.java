package com.diploma.panchayatapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Andria on 6/12/2022.
 */

@Entity(tableName = "table_panchayat")
public class PanchayatModel {
    @PrimaryKey(autoGenerate = true)
    public int panchayatid;
    public String panchayaName;
    public String panchayaArea;
    public String panchayaPop;
    public String panchayaDensityOPop;
    public String panchayaWards;
    public String panchayaHouseHolds;
    public String panchayaBlk;
    public String panchayaParliament;
    public String panchayaAssembly;
    public int villages;
    public String villageNames;
    public String url;

    public PanchayatModel(String panchayaName, String panchayaArea, String panchayaPop,
                          String panchayaDensityOPop, String panchayaWards, String panchayaHouseHolds,
                          String panchayaBlk, String panchayaParliament, String panchayaAssembly,
                          int villages, String villageNames,String url) {
        this.panchayaName = panchayaName;
        this.panchayaArea = panchayaArea;
        this.panchayaPop = panchayaPop;
        this.panchayaDensityOPop = panchayaDensityOPop;
        this.panchayaWards = panchayaWards;
        this.panchayaHouseHolds = panchayaHouseHolds;
        this.panchayaBlk = panchayaBlk;
        this.panchayaParliament = panchayaParliament;
        this.panchayaAssembly = panchayaAssembly;
        this.villages = villages;
        this.villageNames = villageNames;
        this.url=url;
    }

    public String getPanchayaName() {
        return panchayaName;
    }

    public void setPanchayaName(String panchayaName) {
        this.panchayaName = panchayaName;
    }

    public String getPanchayaArea() {
        return panchayaArea;
    }

    public void setPanchayaArea(String panchayaArea) {
        this.panchayaArea = panchayaArea;
    }

    public String getPanchayaPop() {
        return panchayaPop;
    }

    public void setPanchayaPop(String panchayaPop) {
        this.panchayaPop = panchayaPop;
    }

    public String getPanchayaDensityOPop() {
        return panchayaDensityOPop;
    }

    public void setPanchayaDensityOPop(String panchayaDensityOPop) {
        this.panchayaDensityOPop = panchayaDensityOPop;
    }

    public String getPanchayaWards() {
        return panchayaWards;
    }

    public void setPanchayaWards(String panchayaWards) {
        this.panchayaWards = panchayaWards;
    }

    public String getPanchayaHouseHolds() {
        return panchayaHouseHolds;
    }

    public void setPanchayaHouseHolds(String panchayaHouseHolds) {
        this.panchayaHouseHolds = panchayaHouseHolds;
    }

    public String getPanchayaBlk() {
        return panchayaBlk;
    }

    public void setPanchayaBlk(String panchayaBlk) {
        this.panchayaBlk = panchayaBlk;
    }

    public String getPanchayaParliament() {
        return panchayaParliament;
    }

    public void setPanchayaParliament(String panchayaParliament) {
        this.panchayaParliament = panchayaParliament;
    }

    public String getPanchayaAssembly() {
        return panchayaAssembly;
    }

    public void setPanchayaAssembly(String panchayaAssembly) {
        this.panchayaAssembly = panchayaAssembly;
    }

    public int getVillages() {
        return villages;
    }

    public void setVillages(int villages) {
        this.villages = villages;
    }

    public String getVillageNames() {
        return villageNames;
    }

    public void setVillageNames(String villageNames) {
        this.villageNames = villageNames;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
