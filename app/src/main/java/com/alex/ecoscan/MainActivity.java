package com.alex.ecoscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.alex.ecoscan.activities.LoginActivity;
import com.alex.ecoscan.activities.OrdersActivity;
import com.alex.ecoscan.activities.SettingsActivity;
import com.alex.ecoscan.managers.NetworkMng;
import com.alex.ecoscan.managers.RandomMng;
import com.alex.ecoscan.managers.SettingsMng;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SettingsMng settingsMng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsMng = new SettingsMng(this);

        Button btnGoScan = findViewById(R.id.m_btn_scan);
        Button btnGoOrders = findViewById(R.id.m_btn_orders);
        ImageButton btnGoSettings = findViewById(R.id.m_btn_settings);


        // if identifier is null -> generate new identifier and set it
        if (settingsMng.getIdentifier() == 0) {
            settingsMng.setIdentifier(RandomMng.getRandomIdentifier());
        }

        // if autoSynch is true -> synch not synch orders
        if (settingsMng.isAutoSynch()) {
            // synch orders
        }


        btnGoScan.setOnClickListener(view -> {
//            DialogMng.inputOrderNum(this, getApplicationContext());
        });
        btnGoOrders.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, OrdersActivity.class)));
        btnGoSettings.setOnClickListener(view -> {
            // if enableLogin is true -> open LoginActivity
            if (settingsMng.isEnableLogin()){
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                // else -> SettingsActivity
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }});


        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        NetworkMng networkMng = new NetworkMng();
        System.out.println(networkMng.isServerAvailable());
    }
}