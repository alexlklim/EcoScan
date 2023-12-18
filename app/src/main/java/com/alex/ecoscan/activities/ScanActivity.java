package com.alex.ecoscan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.adapters.CodeAdapter;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.DialogMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;
import com.alex.ecoscan.model.Code;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.LinkedList;
import java.util.List;

public class ScanActivity extends AppCompatActivity implements CodeAdapter.OnItemClickListener{
    private static final String TAG = "ScanActivity";
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private SettingsMng settingsMng;
    private RoomDB roomDB;
    private List<Code> codeList = new LinkedList<>();
    private String orderNum;
    private RecyclerView recyclerView;
    private CodeAdapter codeAdapter;

    private Context context;
    String orderNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        roomDB = RoomDB.getInstance(context);
        context = getApplicationContext();
        settingsMng = new SettingsMng(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        // for getting and filtering codes
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(getResources().getString(R.string.activity_intent_filter_action));
        registerReceiver(myBroadcastReceiver, filter);


        setOrderNum();
        initializeRecyclerView();
        listenerCompleteOrder();


    }

    private void saveCodeToLocalMemory(String decodedData, String decodedLabelType){
        Log.i(TAG, "try to saveNewCodeToLocalMemory: " + decodedData);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        Code code = filteringData(new Code(
                decodedData, decodedLabelType,
                String.valueOf(currentLocation.getLongitude()),
                String.valueOf(currentLocation.getLatitude())));
        if (code != null){
            codeList.add(code);
            Log.d(TAG, "Code was saved: " + code);
            codeAdapter.notifyDataSetChanged();
        } else {
            Log.d(TAG, "Code was not saved: doesn't match filters : " + code);
            Tost.show(getString(R.string.t_code_not_match_filter), this);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
    private void listenerCompleteOrder() {
        Log.i(TAG, "addListenerForFinish: ");
        Button btnComplete = findViewById(R.id.sc_btn_complete);
        btnComplete.setOnClickListener(v ->
                DialogMng.confirmCompleteScan(this, getApplicationContext(), roomDB, orderNumber, codeList));
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.sc_rv_codes);
        codeAdapter = new CodeAdapter(codeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(codeAdapter);
    }

    private void setOrderNum() {
        TextView orderNum = findViewById(R.id.sc_tv_orderNum);
        orderNumber = getIntent().getStringExtra("ORDER_NUM");
        orderNum.setText(orderNumber);
    }


    private final BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        // get events (codes)
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "BroadcastReceiver");
            String action = intent.getAction();
            assert action != null;
            if (action.equals(getResources().getString(R.string.activity_intent_filter_action))) {
                try {handleScanResult(intent);
                } catch (Exception e) {e.printStackTrace();}
            }
        }
    };

    private void handleScanResult(Intent initiatingIntent) {
        // get code and label type from event
        Log.i(TAG, "handleScanResult");
        String decodedSource = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_source));
        String decodedData = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_data));
        String decodedLabelType = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_label_type));
        if (decodedSource == null) {
            Log.d(TAG, "handleScanResult: decodedSource == null");
            decodedData = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_data_legacy));
            decodedLabelType = initiatingIntent.getStringExtra(getResources().getString(R.string.datawedge_intent_key_label_type_legacy));
        }
        saveCodeToLocalMemory(decodedData, decodedLabelType);
    }

    private void getLastLocation() {
        Log.i(TAG, "getLastLocation");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            int FINE_PERMISSION_CODE = 1;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;}
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {if (location != null) {currentLocation = location;}});
    }




    @Override
    public void onItemClick(Code code) {
        Log.i(TAG, "onItemClick: ");
        System.out.println("Code: " + code.getCode());
    }



    public Code filteringData(Code code) {
        Log.i(TAG, "filteringData: code");
        if (settingsMng.isAllowNonUniqueCode() && codeList.stream().anyMatch(c -> c.getCode().equals(code.getCode()))) {
            Log.d(TAG, "filteringData: CODE NON UNIQUE=");
            return null;
        }

        if (settingsMng.isCheckLength()) {
            Log.d(TAG, "filteringData: isCheckCodeLength=" + settingsMng.isCheckLength());
            int length = code.getCode().length();
            if (settingsMng.getLength() != 0 && settingsMng.getLength() != length) return null;
            if (settingsMng.getLengthMIN() != 0 && settingsMng.getLengthMIN() < length) return null;
            if (settingsMng.getLengthMAX() != 0 && settingsMng.getLengthMAX() > length) return null;
        }

        if (settingsMng.isAdvancedFilter()) {
            Log.d(TAG, "filteringData: isDoAdvancedFilter=" + settingsMng.isAdvancedFilter());
            String cod = code.getCode();
            if (settingsMng.getPrefix().equals("") && !cod.startsWith(settingsMng.getPrefix())) return null;
            if (settingsMng.getSuffix().equals("") && !cod.contains(settingsMng.getSuffix())) return null;
            if (settingsMng.getEnding().equals("") && !cod.endsWith(settingsMng.getEnding())) return null;
//            if (settingsMng.getLabels().equals("NONE") && !code.getLabel().equals(settingsMng.getLabels())) return null;
        }
        Log.d(TAG, "filteringData: code passed all filters");
        return code;
    }
}