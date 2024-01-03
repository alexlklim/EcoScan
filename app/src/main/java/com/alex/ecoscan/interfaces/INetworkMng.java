package com.alex.ecoscan.interfaces;

import android.content.Context;

public interface INetworkMng {

    // Method to check if the device has an active internet connection
    boolean isNetworkAvailable(Context context);
    boolean isServerAvailable(String serverURL);

    boolean sendJsonData(String json);
}
