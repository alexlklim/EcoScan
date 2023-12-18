package com.alex.ecoscan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alex.ecoscan.model.utiles.Util;

@Entity(tableName = Util.TABLE_NAME_ORDER)
public class Order {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "orderNum")
    private String orderNum;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "timer")
    private String timer;

    @ColumnInfo(name = "isSynch")
    private String isSynch;

    @ColumnInfo(name = "isHidden")
    private int isHidden;

}
