package com.alex.ecoscan.interfaces.activities;

import com.alex.ecoscan.model.Order;

public interface IOrderActivity {

    void deleteOrder(int idOrder);
    void synchWithServer();

    // get orderID and if doesnt exist in DB finish this activity and Toast.orderNotFind
    void checkIfOrderExist();
}
