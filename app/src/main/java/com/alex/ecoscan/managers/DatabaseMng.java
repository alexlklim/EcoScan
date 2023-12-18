package com.alex.ecoscan.managers;

import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.model.Code;
import com.alex.ecoscan.model.Order;

import java.util.List;

public class DatabaseMng {

    public static boolean saveNewOrder(RoomDB roomDB, String orderNum, List<Code> codeList){
        // save new order in DB
        Order order = new Order(orderNum);
        roomDB.orderDAO().insert(order);
        int idOrder = roomDB.orderDAO().getOrderIDByOrderNum(orderNum);

        for (Code code: codeList){
            code.setOrderID(idOrder);
            roomDB.codeDAO().insert(code);
        }

        return true;
    }
}
