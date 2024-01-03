package com.alex.ecoscan.interfaces;

import com.alex.ecoscan.model.Order;

import java.util.List;

public interface ISynchMng {

    void synchOrders(List<Order> list);


    boolean checkCommonCases();

    void changeToSynchOrders(List<Order> list);
}
