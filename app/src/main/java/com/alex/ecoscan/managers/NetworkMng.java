package com.alex.ecoscan.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alex.ecoscan.interfaces.INetworkMng;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkMng implements INetworkMng {
    private static final String TAG = "NetworkMng";

    @Override
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    @Override
    public boolean isServerAvailable(String serverURL) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(serverURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // timeout in milliseconds

            int responseCode = connection.getResponseCode();

            // Check if the response code indicates success (2xx)
            return responseCode >= 200 && responseCode < 300;

        } catch (IOException e) {
            // An error occurred (e.g., connection timeout)
            return false;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public boolean sendJsonData(String json) {
        return false;
    }
}
