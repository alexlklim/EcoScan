package com.alex.ecoscan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alex.ecoscan.model.utiles.Util;

import java.io.Serializable;

@Entity(tableName = Util.TABLE_NAME_CODE)
public class Code implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "code")
    private String code;
    @ColumnInfo(name ="time")
    private String time;
    @ColumnInfo(name = "label")
    private String label;
    @ColumnInfo(name = "longitude")
    private String gpsLong;
    @ColumnInfo(name = "latitude")
    private String gpsLat;
    @ColumnInfo(name = "orderID")
    private int orderID;

}
