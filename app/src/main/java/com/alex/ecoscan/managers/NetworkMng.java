package com.alex.ecoscan.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class NetworkMng{
    private static final String TAG = "NetworkMng";




    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        return false;
    }



    public static int doHttpsGetRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.connect();
            int status = conn.getResponseCode();
            conn.disconnect();
            return status;
        } catch (Exception e) {
            Log.e(TAG, "doHttpsGetRequest", e);
        }
        return 0;
    }










    public static boolean isServerAvailable(String serverURL) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(serverURL);
            Log.e(TAG, "isServerAvailable " + serverURL );
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

}
