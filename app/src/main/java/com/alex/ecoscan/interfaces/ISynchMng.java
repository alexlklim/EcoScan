package com.alex.ecoscan.interfaces;

import com.alex.ecoscan.model.Order;

import java.util.List;

public interface ISynchMng {

    void synchOrder(Order order);
    void synchOrders(List<Order> list);
    void synchAll();

    boolean checkCommonCases();


    void changeToSynchOrder(Order order);
    void changeToSynchOrders(List<Order> list);
}
