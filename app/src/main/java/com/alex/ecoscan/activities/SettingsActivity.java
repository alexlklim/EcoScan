package com.alex.ecoscan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import com.alex.ecoscan.R;
import com.alex.ecoscan.dialogs.DialogAdvancedFilter;
import com.alex.ecoscan.dialogs.DialogLength;
import com.alex.ecoscan.interfaces.activities.ISettingsActivity;
import com.alex.ecoscan.managers.SettingsMng;

import java.util.HashSet;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity
        implements DialogLength.DialogLengthListener, DialogAdvancedFilter.DialogAdvancedFilterListener {
    CheckBox set_isAllowNonUniqueCode, set_isCheckLength, set_isAdvancedFilter;
    private SettingsMng settingsMng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsMng = new SettingsMng(this);
        initializeFilterConfig();


    }

    private void initializeFilterConfig() {
        set_isAllowNonUniqueCode = findViewById(R.id.set_isAllowNonUniqueCode);
        set_isCheckLength = findViewById(R.id.set_isCheckLength);
        set_isAdvancedFilter = findViewById(R.id.set_isAdvancedFilter);

        set_isAllowNonUniqueCode.setChecked(settingsMng.isAllowNonUniqueCode());
        set_isCheckLength.setChecked(settingsMng.isCheckLength());
        set_isAdvancedFilter.setChecked(settingsMng.isAdvancedFilter());

        // add listeners for them
        set_isAllowNonUniqueCode.setOnCheckedChangeListener((btnView, isChecked) -> settingsMng.setIsAllowNonUniqueCode(isChecked));

        set_isCheckLength.setOnCheckedChangeListener((btnView, isChecked) -> {
            if (isChecked) openDialogLength();
            else settingsMng.setIsCheckLength(false);
        });

        set_isAdvancedFilter.setOnCheckedChangeListener((btnView, isChecked) -> {
            if (isChecked) openDialogAdvancedFilter();
            else settingsMng.setIsAdvancedFilter(false);
        });
    }



    // for opening dialogs
    public void openDialogLength(){
        Log.i("TAG", "openDialogLength: ");
        DialogLength dialog = new DialogLength();
        dialog.show(getSupportFragmentManager(), "Length dialog");
    }
    private void openDialogAdvancedFilter() {
        DialogAdvancedFilter dialog = new DialogAdvancedFilter();
        dialog.show(getSupportFragmentManager(), "Advanced Filter dialog");
    }



    // listeners
    @Override
    public void applyLengthConfig(int length, int lengthMin, int lengthMax) {
        Log.i("TAG", "applyLengthConfig: " + length + " " + lengthMin);
        settingsMng.setIsCheckLength(true);
        settingsMng.setBlockLength(length, lengthMin, lengthMax);
    }

    @Override
    public void applyAdvancedFilterConfig(String prefix, String suffix, String ending, HashSet<String> labels) {
        Log.i("TAG", "applyAdvancedFilterConfig: " + prefix + " " + suffix);
        settingsMng.setIsAdvancedFilter(true);
        settingsMng.setBlockAdvancedFilter(prefix, suffix, ending, labels);
    }
}