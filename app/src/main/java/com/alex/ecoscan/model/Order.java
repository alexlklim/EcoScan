package com.alex.ecoscan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alex.ecoscan.managers.DateMng;
import com.alex.ecoscan.model.utiles.Util;

@Entity(tableName = Util.TABLE_NAME_ORDER)
public class Order {
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "orderNum")
    private String orderNum;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "isSynch")
    private int isSynch;

    @ColumnInfo(name = "isHidden")
    private int isHidden;

    public Order() {
    }

    @Ignore
    public Order(String orderNum) {
        this.orderNum = orderNum;
        this.date = DateMng.getCurrentTimeLikeString();
        this.isSynch  = 0;
        this.isHidden = 0;
    }

    @Ignore
    public Order(int ID, String orderNum, String date, int isSynch, int isHidden) {
        this.ID = ID;
        this.orderNum = orderNum;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsSynch() {
        return isSynch;
    }

    public void setIsSynch(int isSynch) {
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
                ", date='" + date + '\'' +
                ", isSynch='" + isSynch + '\'' +
                ", isHidden=" + isHidden +
                '}';
    }
}
