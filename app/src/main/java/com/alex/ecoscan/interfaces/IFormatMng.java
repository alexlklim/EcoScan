package com.alex.ecoscan.interfaces;

import android.location.Location;

import com.alex.ecoscan.model.Order;

import java.util.List;

public interface IFormatMng {

    String getJsonFromOrder(Order order);
    String getJsonFromOrders(List<Order> list);

    int parseIntFromStringOrDefaultZero(String value);


}
