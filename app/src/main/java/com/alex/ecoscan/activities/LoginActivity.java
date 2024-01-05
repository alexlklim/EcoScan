package com.alex.ecoscan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.ecoscan.R;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText login, password;
    TextView login_result;
    Button btn_login;

    SettingsMng sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sm = new SettingsMng(this);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        login_result = findViewById(R.id.login_result);


        btn_login.setOnClickListener(v -> {
            if (sm.getLogin().equals(login.getText().toString()) && sm.getPassword().equals(password.getText().toString())){
                startActivity(new Intent(LoginActivity.this, SettingsActivity.class));
            }
            else{
                login_result.setText(R.string.login_or_password_are_incorrect);
                Tost.show(getString(R.string.login_or_password_are_incorrect), this);
            }
        });
    }
}