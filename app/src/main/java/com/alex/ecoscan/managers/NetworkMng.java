package com.alex.ecoscan.managers;

import com.alex.ecoscan.interfaces.INetworkMng;

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
