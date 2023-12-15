package com.alex.ecoscan.interfaces;

public interface INetworkMng {

    boolean isNetworkAvailable();
    boolean isServerAvailable();

    boolean sendJsonData(String json);
}
