package com.alex.ecoscan.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alex.ecoscan.model.Code;

import java.util.List;

public interface CodeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Code code);

    @Query("SELECT * FROM codes")
    List<Code> getAll();

    @Query("SELECT * FROM codes WHERE orderID = :orderID")
    List<Code> getAllByOrderID(int orderID);

    @Delete
    void delete(Code code);
}
