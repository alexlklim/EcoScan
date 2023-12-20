package com.alex.ecoscan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alex.ecoscan.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}