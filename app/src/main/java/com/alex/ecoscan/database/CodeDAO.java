package com.alex.ecoscan.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alex.ecoscan.model.Code;
import com.alex.ecoscan.model.utiles.Util;

import java.util.List;

@Dao
public interface CodeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Code code);

    @Query("SELECT * FROM codes WHERE orderID = :orderID")
    List<Code> getAllByOrderID(int orderID);

    @Query("SELECT COUNT(*) FROM " + Util.TABLE_NAME_CODE + " WHERE orderID = :orderId")
    int getCodeCountForOrder(int orderId);



    @Delete
    void delete(Code code);

    @Query("DELETE FROM codes")
    void deleteAllCodes();
}
