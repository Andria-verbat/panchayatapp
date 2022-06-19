package com.diploma.panchayatapp.localdb;





import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.diploma.panchayatapp.model.ApplicationModel;
import com.diploma.panchayatapp.model.PanchayatModel;
import com.diploma.panchayatapp.model.ProjectModel;

import java.util.List;

@Dao
public interface EGramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(ProjectModel projectModel);

    @Query("SELECT * FROM table_projecs WHERE panchayatName =:panchaytName ORDER BY pid DESC")
    public List<ProjectModel> getProjects(String panchaytName);

    @Query("SELECT * FROM table_projecs ORDER BY pid DESC")
    public List<ProjectModel> getAllProjects();

    @Query("SELECT profImg FROM table_application_form WHERE mobNum=:mob")
    String getImg(Long mob);

    @Insert
    void insertApplnForm(ApplicationModel applicationModel);

    @Query("UPDATE table_application_form SET pwd=:pwd WHERE mobNum = :phone")
    void update(Long phone, String pwd);

    @Query("SELECT mobNum FROM table_application_form")
    List<Long> getPhoneNUmber();

    @Query("SELECT * FROM table_application_form WHERE mobNum =:uname AND pwd =:pwd")
    public List<ApplicationModel> getUser(Long uname,String pwd);

    @Query("SELECT * FROM table_application_form WHERE mobNum =:mob")
    public List<ApplicationModel> getUserDetails(Long mob);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPanchayatDetails(PanchayatModel panchayatModel);

    @Query("DELETE FROM table_panchayat")
    void deletePanchayatDetails();

    @Query("SELECT * FROM table_panchayat WHERE panchayaName =:panchaytName")
    public List<PanchayatModel> getPanchayatDetails(String panchaytName);

    @Query("UPDATE table_panchayat SET url= :url WHERE panchayaName= :panchaytName")
    void updatePanchayat(String panchaytName,String url);

    @Query("SELECT * FROM table_panchayat")
    public List<PanchayatModel> getAllPanchayatDetails();

}
