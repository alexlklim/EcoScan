package com.alex.ecoscan.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.ecoscan.model.Order;

import java.util.List;

@Dao
public interface OrderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Query("SELECT * FROM orders ORDER BY id DESC")
    List<Order> getAll();

    @Query("SELECT * FROM orders WHERE isSynch = 0")
    List<Order> getNonSynch();



    @Query("SELECT * FROM orders WHERE id = :orderID")
    Order getOrderByOrderID(int orderID);

    @Query(("SELECT ID FROM orders WHERE orderNum = :orderNum"))
    int getOrderIDByOrderNum(String orderNum);

    @Query("SELECT EXISTS(SELECT 1 FROM orders WHERE orderNum= :orderNum LIMIT 1)")
    boolean isExistByOrderNum(String orderNum);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("DELETE FROM orders WHERE isSynch = 1")
    void deleteAllSynch();


    @Query("DELETE FROM orders")
    void deleteAllOrders();

}
