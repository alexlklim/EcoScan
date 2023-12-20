package com.alex.ecoscan.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.alex.ecoscan.R;
import com.alex.ecoscan.dialogs.DialogAdvancedFilter;
import com.alex.ecoscan.dialogs.DialogConfirmSentAllData;
import com.alex.ecoscan.dialogs.DialogLength;
import com.alex.ecoscan.dialogs.DialogServerConfig;
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;
import com.alex.ecoscan.model.enums.Lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SettingsActivity extends AppCompatActivity
        implements
        DialogLength.DialogLengthListener,
        DialogAdvancedFilter.DialogAdvancedFilterListener,
        DialogConfirmSentAllData.DialogConfirmListener
{
    CheckBox set_isAllowNonUniqueCode, set_isCheckLength, set_isAdvancedFilter;
    LinearLayout ll_isAllowNonUniqueCode, ll_isCheckLength, ll_isAdvancedFilter;


    CheckBox set_isAllowEditCode, set_isAllowEditOrder, set_isAddGPS, set_isEnableLogging;
    LinearLayout ll_isAllowEditCode, ll_isAllowEditOrders, ll_isAddGPS, ll_isEnableLogging;


    CheckBox set_isSendData, set_isAutoSynch;
    LinearLayout ll_isSentData, ll_isAutoSynch, ll_configureServer;
    TextView set_configureServer;


    LinearLayout ll_identifier, ll_lang;
    Spinner set_lang;
    EditText set_identifier;
    ImageView set_btn_saveClientConfig;


    LinearLayout ll_isHideSynchData;
    CheckBox set_isHideSynchData;
    TextView set_sendAllData, set_deleteSynchData, set_clearAllData;

    private SettingsMng settingsMng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsMng = new SettingsMng(this);

        initializeFilterConfig();
        initializeAdminConfig();
        initializeServerConfig();
        initializeClientConfig();
        initializeDataManagementConfig();

    }

    private void initializeDataManagementConfig() {
        set_isHideSynchData = findViewById(R.id.set_isHideSynchData);
        set_sendAllData = findViewById(R.id.set_sendAllData);
        set_deleteSynchData = findViewById(R.id.set_deleteSynchData);
        set_clearAllData = findViewById(R.id.set_clearAllData);
        ll_isHideSynchData = findViewById(R.id.ll_isHideSynchData);

        set_isHideSynchData.setChecked(settingsMng.isHideSynchData());

        ll_isHideSynchData.setOnClickListener(v -> changeColor(ll_isHideSynchData));
        set_isHideSynchData.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isHideSynchData);
            settingsMng.setIsHideSynchData(isChecked);
        });

        set_sendAllData.setOnClickListener(v -> {
            changeColorForButton(set_sendAllData);
            openDialogConfirmSentAllDataToServer();
        });
        set_deleteSynchData.setOnClickListener(v -> {
            changeColorForButton(set_deleteSynchData);
            openDialogConfirmSentAllDataToServer();
        });

        set_clearAllData.setOnClickListener(v -> {
            changeColorForButton(set_clearAllData);
            openDialogConfirmSentAllDataToServer();
        });

    }


    private void initializeFilterConfig() {
        set_isAllowNonUniqueCode = findViewById(R.id.set_isAllowNonUniqueCode);
        set_isCheckLength = findViewById(R.id.set_isCheckLength);
        set_isAdvancedFilter = findViewById(R.id.set_isAdvancedFilter);

        set_isAllowNonUniqueCode.setChecked(settingsMng.isAllowNonUniqueCode());
        set_isCheckLength.setChecked(settingsMng.isCheckLength());
        set_isAdvancedFilter.setChecked(settingsMng.isAdvancedFilter());

        ll_isAllowNonUniqueCode = findViewById(R.id.ll_isAllowNonUniqueCode);
        ll_isCheckLength = findViewById(R.id.ll_isCheckLength);
        ll_isAdvancedFilter = findViewById(R.id.ll_isAdvancedFilter);


        // LinearLayout Allow Non unique Code
        ll_isAllowNonUniqueCode.setOnClickListener(view -> changeColor(ll_isAllowNonUniqueCode));
        set_isAllowNonUniqueCode.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isAllowNonUniqueCode);
            settingsMng.setIsAllowNonUniqueCode(isChecked);
        });

        // LinearLayout isCheckLength
        ll_isCheckLength.setOnClickListener(view -> changeColor(ll_isCheckLength));
        set_isCheckLength.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isCheckLength);
            if (isChecked) openDialogLength();
            else settingsMng.setIsCheckLength(false);
        });


        // LinearLayout isAdvancedFilter
        ll_isAdvancedFilter.setOnClickListener(view -> changeColor(ll_isAdvancedFilter));
        set_isAdvancedFilter.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isAdvancedFilter);
            if (isChecked) openDialogAdvancedFilter();
            else settingsMng.setIsAdvancedFilter(false);
        });
    }
    private void initializeAdminConfig() {
        set_isAllowEditCode = findViewById(R.id.set_isAllowEditCode);
        set_isAllowEditOrder = findViewById(R.id.set_isAllowEditOrder);
        set_isAddGPS = findViewById(R.id.set_isAddGPS);
        set_isEnableLogging = findViewById(R.id.set_isEnableLogging);

        set_isAllowEditCode.setChecked(settingsMng.isAllowEditCode());
        set_isAllowEditOrder.setChecked(settingsMng.isAllowEditOrders());
        set_isAddGPS.setChecked(settingsMng.isAddGPS());
        set_isEnableLogging.setChecked(settingsMng.isEnableLogin());


        ll_isAllowEditCode = findViewById(R.id.ll_isAllowEditCode);
        ll_isAllowEditOrders = findViewById(R.id.ll_isAllowEditOrders);
        ll_isAddGPS = findViewById(R.id.ll_isAddGPS);
        ll_isEnableLogging = findViewById(R.id.ll_isEnableLogging);


        ll_isAllowEditCode.setOnClickListener(v -> changeColor(ll_isAllowEditCode));
        set_isAllowEditCode.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isAllowEditCode);
            settingsMng.setIsAllowEditCode(isChecked);
        });

        ll_isAllowEditOrders.setOnClickListener(v -> changeColor(ll_isAllowEditOrders));
        set_isAllowEditOrder.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isAllowEditOrders);
            settingsMng.setIsAllowEditOrders(isChecked);
        });

        ll_isAddGPS.setOnClickListener(v -> changeColor(ll_isAddGPS));
        set_isAddGPS.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isAddGPS);
            settingsMng.setIsAddGPSToCode(isChecked);
        });

        ll_isEnableLogging.setOnClickListener(v -> changeColor(ll_isEnableLogging));
        set_isEnableLogging.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isEnableLogging);
            settingsMng.setIsEnableLogin(isChecked);
        });

    }
    private void initializeServerConfig() {
        set_isSendData = findViewById(R.id.set_isSendData);
        set_isAutoSynch = findViewById(R.id.set_isAutoSynch);

        set_configureServer = findViewById(R.id.set_configureServer);

        ll_isSentData = findViewById(R.id.ll_isSentData);
        ll_isAutoSynch = findViewById(R.id.ll_isAutoSynch);
        ll_configureServer = findViewById(R.id.ll_configureServer);

        set_isSendData.setChecked(settingsMng.isSentData());
        set_isAutoSynch.setChecked(settingsMng.isAutoSynch());

        ll_isSentData.setOnClickListener(v -> changeColor(ll_isSentData));
        set_isSendData.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isSentData);
            settingsMng.setIsSendData(isChecked);
        });

        ll_isAutoSynch.setOnClickListener(v -> changeColor(ll_isAutoSynch));
        set_isAutoSynch.setOnCheckedChangeListener((btnView, isChecked) -> {
            changeColor(ll_isAutoSynch);
            settingsMng.setIsAutoSynch(isChecked);
        });

        set_configureServer.setOnClickListener(v -> {
            changeColor(ll_configureServer);
            openDialogServerConfig();
        });


    }
    private void initializeClientConfig() {
        set_identifier = findViewById(R.id.set_identifier);
        set_lang = findViewById(R.id.set_lang);
        set_btn_saveClientConfig = findViewById(R.id.set_btn_saveClientConfig);

        ll_identifier = findViewById(R.id.ll_identifier);
        ll_lang = findViewById(R.id.ll_lang);

        set_identifier.setText(String.valueOf(settingsMng.getIdentifier()));

        // initialize spinner Lang
        List<String> languages = Lang.getListLang();
        int indexLang = languages.indexOf( Lang.getLanguageByCode(settingsMng.getLang()));
        ArrayAdapter<String> adapterLang = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(languages));
        adapterLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        set_lang.setAdapter(adapterLang);
        if (indexLang != -1) set_lang.setSelection(indexLang);

        ll_identifier.setOnClickListener(view -> changeColor(ll_identifier));
        ll_lang.setOnClickListener(view -> changeColor(ll_lang));

        FormatMng formatMng = new FormatMng();
        set_btn_saveClientConfig.setOnClickListener(v -> {
            set_btn_saveClientConfig.setBackgroundColor(Color.LTGRAY);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    set_btn_saveClientConfig.setBackgroundColor(Color.TRANSPARENT); // or set your original color
                }
            }, 300);
            settingsMng.setIdentifier(formatMng.parseIntFromStringOrDefaultZero(set_identifier.getText().toString()));
            settingsMng.setLang(Lang.getCodeByLanguage(set_lang.getSelectedItem().toString()));
            initializeClientConfig();
            Tost.show("Saved", this);
            if (settingsMng.getIdentifier() == 0) Tost.show(getString(R.string.something_wrong), this);
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
    private void openDialogServerConfig() {
        Log.i("TAG", "openDialogServerConfig: ");
        DialogServerConfig dialog = new DialogServerConfig();
        dialog.show(getSupportFragmentManager(), "Server config dialog");
    }
    private void openDialogConfirmSentAllDataToServer() {
        Log.i("TAG", "openDialogServerConfig: ");
        DialogConfirmSentAllData dialog = new DialogConfirmSentAllData();
        dialog.show(getSupportFragmentManager(), "Confirm to sent all data to server dialog");
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



    private void changeColor(LinearLayout layout) {
        layout.setBackgroundColor(Color.LTGRAY);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int color = ContextCompat.getColor(getApplicationContext(), R.color.elements);
                layout.setBackgroundColor(color);
            }
        }, 600);
    }

    private void changeColor(TextView layout) {
        layout.setBackgroundColor(Color.LTGRAY);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int color = ContextCompat.getColor(getApplicationContext(), R.color.elements);
                layout.setBackgroundColor(color);
            }
        }, 300);
    }
    private void changeColorForButton(TextView layout) {
        layout.setBackgroundColor(Color.LTGRAY);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int color = ContextCompat.getColor(getApplicationContext(), R.color.btnSettings);
                layout.setBackgroundColor(color);
            }
        }, 300);
    }

    @Override
    public void returnAnswer(boolean answer) {

    }
}