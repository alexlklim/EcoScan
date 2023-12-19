package com.alex.ecoscan.managers;

import com.alex.ecoscan.interfaces.INetworkMng;
import com.alex.ecoscan.model.utiles.Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkMng implements INetworkMng {
    @Override
    public boolean isNetworkAvailable() {
        return false;
    }

    @Override
    public boolean isServerAvailable() {
        return false;
    }

    @Override
    public boolean sendJsonData(String json) {
        return false;
    }
}
