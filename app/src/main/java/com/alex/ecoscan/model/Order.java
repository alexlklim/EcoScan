package com.alex.ecoscan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alex.ecoscan.model.utiles.Util;

@Entity(tableName = Util.TABLE_NAME_ORDER)
public class Order {
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int ID;

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

    public Order() {
    }

    @Ignore
    public Order(String orderNum, String startTime, String timer, String isSynch, int isHidden) {
        this.orderNum = orderNum;
        this.startTime = startTime;
        this.timer = timer;
        this.isSynch = isSynch;
        this.isHidden = isHidden;
    }

    @Ignore
    public Order(int ID, String orderNum, String startTime, String timer, String isSynch, int isHidden) {
        this.ID = ID;
        this.orderNum = orderNum;
        this.startTime = startTime;
        this.timer = timer;
        this.isSynch = isSynch;
        this.isHidden = isHidden;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getIsSynch() {
        return isSynch;
    }

    public void setIsSynch(String isSynch) {
        this.isSynch = isSynch;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + ID +
                ", orderNum='" + orderNum + '\'' +
                ", startTime='" + startTime + '\'' +
                ", timer='" + timer + '\'' +
                ", isSynch='" + isSynch + '\'' +
                ", isHidden=" + isHidden +
                '}';
    }
}
