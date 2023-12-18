package com.alex.ecoscan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alex.ecoscan.managers.DateMng;
import com.alex.ecoscan.model.utiles.Util;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = Util.TABLE_NAME_CODE)
public class Code implements Serializable {
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "code")
    private String code;
    @ColumnInfo(name ="time")
    private String time;
    @ColumnInfo(name = "label")
    private String label;
    @ColumnInfo(name = "longitude")
    private String gpsLon;
    @ColumnInfo(name = "latitude")
    private String gpsLat;
    @ColumnInfo(name = "orderID")
    private int orderID;

    public Code() {
    }

    @Ignore
    public Code(int ID, String code, String time, String label, String gpsLon, String gpsLat, int orderID) {
        this.ID = ID;
        this.code = code;
        this.time = time;
        this.label = label;
        this.gpsLon = gpsLon;
        this.gpsLat = gpsLat;
        this.orderID = orderID;
    }

    @Ignore
    public Code(String code, String label, String gpsLon, String gpsLat) {
        this.code = code;
        this.time = DateMng.getCurrentTimeLikeString();
        this.label = label;
        this.gpsLon = gpsLon;
        this.gpsLat = gpsLat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGpsLon() {
        return gpsLon;
    }

    public void setGpsLon(String gpsLon) {
        this.gpsLon = gpsLon;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + ID +
                ", code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", label='" + label + '\'' +
                ", gpsLong='" + gpsLon + '\'' +
                ", gpsLat='" + gpsLat + '\'' +
                ", orderID=" + orderID +
                '}';
    }
}
