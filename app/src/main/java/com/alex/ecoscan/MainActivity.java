package com.alex.ecoscan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;

import com.alex.ecoscan.activities.LoginActivity;
import com.alex.ecoscan.activities.OrdersActivity;
import com.alex.ecoscan.activities.SettingsActivity;
import com.alex.ecoscan.managers.DialogMng;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context context;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoScan = findViewById(R.id.m_btn_scan);
        Button btnGoOrders = findViewById(R.id.m_btn_orders);
        ImageButton btnGoSettings = findViewById(R.id.m_btn_settings);

        // if identifier is null -> generate new identifier and set it
        // if autoSynch is true -> synch not synch orders



        btnGoScan.setOnClickListener(view -> {
            DialogMng.inputOrderNum(this, getApplicationContext());
        });
        btnGoOrders.setOnClickListener(view -> 
                startActivity(new Intent(MainActivity.this, OrdersActivity.class)));
        btnGoSettings.setOnClickListener(view ->
                // if enableLogin is true -> open LoginActivity
                // else -> SettingsActivity
                startActivity(new Intent(MainActivity.this, SettingsActivity.class)));

    }
}