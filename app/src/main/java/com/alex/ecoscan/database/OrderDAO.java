package com.alex.ecoscan.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.ecoscan.model.Order;

import java.util.List;

public interface OrderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Query("SELECT * FROM orders ORDER BY id DESC")
    List<Order> getAll();

    @Query("SELECT * FROM orders WHERE isSynch = 0")
    List<Order> getNonSynch();

    @Query("SELECT * FROM orders WHERE orderNum= :orderNum")
    Order getByOrderNum(String orderNum);

    @Query("SELECT * FROM orders WHERE id = :orderID")
    Order getByOrderID(int orderID);

    @Query("SELECT EXISTS(SELECT 1 FROM orders WHERE orderNum= :orderNum LIMIT 1)")
    boolean isExistByOrderNum(String orderNum);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("DELETE FROM orders WHERE isSynch = 1")
    void deleteAllSynch();


}
