package com.alex.ecoscan.dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.alex.ecoscan.managers.NetworkMng;
import com.alex.ecoscan.managers.SettingsMng;

public class DialogServerConfig extends AppCompatDialogFragment {
    private static final String TAG = "DialogServerConfig";

    SettingsMng settingsMng;
    Button set_btn_checkConnection;
    EditText set_serverAddress;
    ImageView set_connectionResultImage;
    TextView set_connectionResultText;
    static boolean connectionResult;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_server_config, null);
        builder.setView(view).setTitle("Server configuration");
        settingsMng = new SettingsMng(requireContext());

        set_btn_checkConnection = view.findViewById(R.id.set_btn_checkConnection);
        set_serverAddress = view.findViewById(R.id.set_serverAddress);
        set_connectionResultImage = view.findViewById(R.id.set_connectionResultImage);
        set_connectionResultText = view.findViewById(R.id.set_connectionResultText);

        set_serverAddress.setText(settingsMng.getServerAddress());
        set_connectionResultImage.setVisibility(View.INVISIBLE);
        set_connectionResultText.setVisibility(View.INVISIBLE);

        if (settingsMng.isServerConfigured()){
            setIfSuccess();
            setVisible();
        }

        set_btn_checkConnection.setOnClickListener(v ->{
            setInvisible();
            if (!NetworkMng.isNetworkAvailable(requireContext())){
                setIfFail();
                setVisible();
                settingsMng.setIsServerConfigured(false);
            }

            // check connection
            if (NetworkMng.isNetworkAvailable(requireContext())){
                performHttpsRequestInBackground(settingsMng.getServerAddress(), DialogServerConfig.this);
            }

        });

        return builder.create();
    }
    public void setIfSuccess(){
        settingsMng.setIsServerConfigured(true);
        set_connectionResultImage.setImageResource(R.drawable.ic_success);
        set_connectionResultText.setText(R.string.server_was_configured);
        set_connectionResultText.setTextColor(Color.GREEN);
    }
    public void setIfFail(){
        set_connectionResultImage.setImageResource(R.drawable.ic_fail);
        set_connectionResultText.setText(R.string.something_wrong_with_server);
        set_connectionResultText.setTextColor(Color.RED);
    }
    public void setVisible(){
        settingsMng.setServerAddress(set_serverAddress.getText().toString());
        set_connectionResultImage.setVisibility(View.VISIBLE);
        set_connectionResultText.setVisibility(View.VISIBLE);
    }
    public void setInvisible(){
        set_connectionResultImage.setVisibility(View.INVISIBLE);
        set_connectionResultText.setVisibility(View.INVISIBLE);
    }


    public static void performHttpsRequestInBackground(final String url, final DialogServerConfig dialogServerConfig) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int status = NetworkMng.doHttpsGetRequest(url);
                if (status >= 200 && status <= 300) {
                    dialogServerConfig.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialogServerConfig.setIfSuccess();
                            dialogServerConfig.setVisible();
                        }
                    });
                } else {
                    dialogServerConfig.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialogServerConfig.setIfFail();
                            dialogServerConfig.setVisible();

                        }
                    });
                }


                Log.e(TAG, "performHttpsRequestInBackground: " + status);
            }
        }).start();
    }





}


