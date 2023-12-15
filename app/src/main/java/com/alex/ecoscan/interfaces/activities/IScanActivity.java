package com.alex.ecoscan.interfaces.activities;

import android.content.Intent;

import com.alex.ecoscan.model.Code;

import java.util.List;

public interface IScanActivity {

    void handleScanResult(Intent initiatingIntent);

    void getLastLocation();

    Code filter(Code code);
    void addCodeToList(Code code);
    void addOrderToDB(List<Code> codeList);

    void synchIfAutoSynchTrue();


}
