package com.alex.ecoscan.managers;

import com.alex.ecoscan.interfaces.ISynchMng;
import com.alex.ecoscan.model.Order;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SynchMan implements ISynchMng {
    private static final String TAG = "SynchMan";


    @Override
    public void synchOrder(Order order) {


    }

    @Override
    public void synchOrders(List<Order> list) {

    }

    @Override
    public void synchAll() {

    }

    @Override
    public boolean checkCommonCases() {
        return false;
    }

    @Override
    public void changeToSynchOrder(Order order) {

    }

    @Override
    public void changeToSynchOrders(List<Order> list) {

    }
}
