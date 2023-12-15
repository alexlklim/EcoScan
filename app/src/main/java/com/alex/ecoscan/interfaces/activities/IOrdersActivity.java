package com.alex.ecoscan.interfaces.activities;

public interface IOrdersActivity {
    void synchIfAutoSynchTrue();

    void synchNewData();
    void hideSynchData();
    void showSynchData();


}
