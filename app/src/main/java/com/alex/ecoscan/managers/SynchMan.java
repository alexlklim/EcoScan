package com.alex.ecoscan.managers;

import android.content.Context;

import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.interfaces.ISynchMng;
import com.alex.ecoscan.model.Order;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SynchMan implements ISynchMng {
    private static final String TAG = "SynchMan";
    private static Context context;
    RoomDB roomDB;
    SettingsMng sm;
    URL url;

    public SynchMan(Context context) {
        SynchMan.context = context;
    }

    @Override
    public void synchOrder(Order order) {
        if (!checkCommonCases()){
            return;
        }
        System.out.println("Okay");

    }

    @Override
    public void synchOrders(List<Order> list) {

    }

    @Override
    public void synchAll() {

    }

    @Override
    public boolean checkCommonCases() {
        // server is not configured
        sm = new SettingsMng(context);
        if (!sm.isServerConfigured()){
            Tost.show("Server is not configured", context);
            return false;
        }
        // no internet connection
        if (!NetworkMng.isNetworkAvailable(context)){
            Tost.show("No internet connection", context);
            return false;
        }

        // is server available
        if (!NetworkMng.isServerAvailable(sm.getServerAddress())){
            Tost.show("Server isn't available", context);
            return false;
        }

        return true;
    }

    @Override
    public void changeToSynchOrder(Order order) {

    }

    @Override
    public void changeToSynchOrders(List<Order> list) {

    }
}
