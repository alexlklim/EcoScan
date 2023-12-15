package com.alex.ecoscan.interfaces.activities;

public interface ISettingsActivity {
    void synchNewData();
    void synchAllData();
    void deleteAllData();
    void deleteOnlySynchData();



    void getSettings();
    void setSettings();
    void setDefaultSettings();
    void getAndSetConfigurationFromServer();



    boolean checkConnectionWithServer();
    boolean checkLoginAndNewPassword(String login, String oldPW, String newPW);




}
