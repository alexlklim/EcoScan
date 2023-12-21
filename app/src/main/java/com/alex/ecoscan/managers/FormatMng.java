package com.alex.ecoscan.managers;

import android.location.Location;

import com.alex.ecoscan.interfaces.IFormatMng;
import com.alex.ecoscan.model.Order;

import java.util.List;

public class FormatMng implements IFormatMng {
    @Override
    public String getJsonFromOrder(Order order) {
        return null;
    }

    @Override
    public String getJsonFromOrders(List<Order> list) {
        return null;
    }



    @Override
    public int parseIntFromStringOrDefaultZero(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
