package com.alex.ecoscan.managers;

import android.location.Location;

import com.alex.ecoscan.interfaces.IFormatMng;
import com.alex.ecoscan.model.Order;

import java.util.List;

public class FormatMng {

    public int parseIntFromStringOrDefaultZero(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
