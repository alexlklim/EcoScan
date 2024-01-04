package com.alex.ecoscan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.adapters.OrderAdapter;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.DatabaseMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.SynchMan;
import com.alex.ecoscan.model.Code;
import com.alex.ecoscan.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.OnItemClickListener {
    private static final String TAG = "OrderActivity";

    List<Code> codeList;
    Order order;
    RoomDB roomDB;
    int orderId;

    ImageView so_iv_synchStatus, rv_so_iv_del;
    TextView so_tv_dateTime, so_tv_orderNumber;
    SettingsMng settingsMng;

    OrderAdapter orderAdapter;

    AlertDialog alertDialog;
    SynchMan synchMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        settingsMng = new SettingsMng(this);
        roomDB = RoomDB.getInstance(this);
        synchMan = new SynchMan(this);

        Intent intent = getIntent();
        orderId = intent.getIntExtra("ORDER_ID", -1);
        order = roomDB.orderDAO().getOrderByOrderID(orderId);
        codeList = roomDB.codeDAO().getAllByOrderID(orderId);


        initializeTextViews();
        initializeRecyclerView();

        if (settingsMng.isServerConfigured() && settingsMng.isAutoSynch() && settingsMng.isSentData() && order.getIsSynch()== 0){
            synch();
        }
    }

    private void synch() {
        synchMan = new SynchMan(this);
        List<Order> list = new ArrayList<>();
        list.add(roomDB.orderDAO().getOrderByOrderID(orderId));
        synchMan.synchOrders(list, responseCode -> runOnUiThread(this::initializeTextViews));
    }


    private void initializeTextViews() {
        so_tv_orderNumber = findViewById(R.id.so_tv_orderNumber);
        so_tv_dateTime = findViewById(R.id.so_tv_dateTime);
        so_iv_synchStatus = findViewById(R.id.so_iv_synchStatus);

        so_tv_orderNumber.setText(order.getOrderNum());
        so_tv_dateTime.setText(order.getDate());

        if (order.getIsSynch() == 1) so_iv_synchStatus.setImageResource(R.drawable.ic_synch);
        else so_iv_synchStatus.setImageResource(R.drawable.ic_synch_not);
        so_iv_synchStatus.setOnClickListener(v -> synch());


    }

    private void initializeRecyclerView() {
        rv_so_iv_del = findViewById(R.id.ro_delete);
        RecyclerView recyclerView = findViewById(R.id.o_rv_order);
        codeList = roomDB.codeDAO().getAllByOrderID(orderId);
        orderAdapter = new OrderAdapter(codeList, this, settingsMng.isAllowEditOrders());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);
    }


    public void finishActivity(View view){
        finish();
    }

    public void showDialogDeleteOrder(View view){
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_confirm, null);

        TextView d_confirm = dialog.findViewById(R.id.d_confirm);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_no = dialog.findViewById(R.id.btn_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialog);

        if (order.getIsSynch() == 0) d_confirm.setText(R.string.delete_order_without_synchoronization);
        else  d_confirm.setText(R.string.delete_order);

        btn_yes.setOnClickListener(v -> {
            Log.d(TAG, "showDialogConfirmationToDeleting: d_btn_yes");
            DatabaseMng.deleteOrder(roomDB ,order, codeList);
            alertDialog.dismiss();
            finish();

        });
        btn_no.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    public void onItemClick(Code code) {
        if (codeList.size() <= 1){
            showDialogAlert();
        } else {
            roomDB.codeDAO().delete(code);
//            orderAdapter.notifyDataSetChanged();
            initializeRecyclerView();

        }
    }

    private void showDialogAlert() {
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_confirm, null);

        TextView d_confirm = dialog.findViewById(R.id.d_confirm);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_no = dialog.findViewById(R.id.btn_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialog);

        d_confirm.setText(R.string.you_try_to_delete_last_code_in_order_after_this_order_will_delete_automatically);

        btn_yes.setOnClickListener(v -> {
            DatabaseMng.deleteOrder(roomDB ,order, codeList);
            alertDialog.dismiss();
            finish();

        });
        btn_no.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog = builder.create();
        alertDialog.show();
    }
}