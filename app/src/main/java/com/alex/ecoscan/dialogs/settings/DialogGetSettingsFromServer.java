package com.alex.ecoscan.dialogs.settings;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.alex.ecoscan.R;
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;

public class DialogGetSettingsFromServer extends AppCompatDialogFragment {
    private static final String TAG = "DialogGetSettingsFromServer";

    SettingsMng settingsMng;
    Button set_btn_checkConnection;
    EditText set_serverAddress;
    ImageView set_connectionResultImage;
    TextView set_connectionResultText;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_server_config, null);
        builder.setView(view).setTitle("Get settings from server");
        settingsMng = new SettingsMng(requireContext());

        set_btn_checkConnection = view.findViewById(R.id.set_btn_checkConnection);
        set_serverAddress = view.findViewById(R.id.set_serverAddress);
        set_connectionResultImage = view.findViewById(R.id.set_connectionResultImage);
        set_connectionResultText = view.findViewById(R.id.set_connectionResultText);

        set_serverAddress.setText(settingsMng.getServerAddress());
        set_connectionResultImage.setVisibility(View.INVISIBLE);
        set_connectionResultText.setVisibility(View.INVISIBLE);
        set_btn_checkConnection.setText(getString(R.string.get_settings));

        set_btn_checkConnection.setOnClickListener(v ->{
            // check connection
            boolean connectionResult = false;
            settingsMng.setServerAddress(set_serverAddress.getText().toString());
            settingsMng.setIsServerConfigured(connectionResult);
            set_connectionResultImage.setVisibility(View.VISIBLE);
            set_connectionResultText.setVisibility(View.VISIBLE);

            if (connectionResult) {
                set_connectionResultImage.setImageResource(R.drawable.ic_success);
                set_connectionResultText.setText(R.string.settings_was_applied);
                set_connectionResultText.setTextColor(Color.GREEN);

            } else {
                set_connectionResultImage.setImageResource(R.drawable.ic_fail);
                set_connectionResultText.setText(R.string.something_wrong_with_server);
                set_connectionResultText.setTextColor(Color.RED);
            }

        });

        return builder.create();
    }
}
