package com.alex.ecoscan.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.adapters.OrderAdapter;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.DatabaseMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.SynchMan;
import com.alex.ecoscan.model.Code;
import com.alex.ecoscan.model.Order;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.OnItemClickListener {
    private static final String TAG = "OrderActivity";

    List<Code> codeList;
    Order order;
    RoomDB roomDB;
    Context context;
    int orderId;
    Button s_btn_comeBack, s_btn_deleteOrder;
    ImageView so_iv_synchStatus, rv_so_iv_del;
    TextView so_tv_dateTime, so_tv_orderNumber;
    SettingsMng settingsMng;

    OrderAdapter orderAdapter;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        settingsMng = new SettingsMng(this);
        roomDB = RoomDB.getInstance(this);


        Intent intent = getIntent();
        orderId = intent.getIntExtra("ORDER_ID", -1);
        order = roomDB.orderDAO().getOrderByOrderID(orderId);
        codeList = roomDB.codeDAO().getAllByOrderID(orderId);


        initializeTextViews();
        initializeRecyclerView();


    }


    private void initializeTextViews() {
        so_tv_orderNumber = findViewById(R.id.so_tv_orderNumber);
        so_tv_dateTime = findViewById(R.id.so_tv_dateTime);
        so_iv_synchStatus = findViewById(R.id.so_iv_synchStatus);

        so_tv_orderNumber.setText(order.getOrderNum());
        so_tv_dateTime.setText(order.getDate());

        if (order.getIsSynch() == 1) so_iv_synchStatus.setImageResource(R.drawable.ic_synch);
        else so_iv_synchStatus.setImageResource(R.drawable.ic_synch_not);


    }

    private void initializeRecyclerView() {
        rv_so_iv_del = findViewById(R.id.ro_delete);
        RecyclerView recyclerView = findViewById(R.id.o_rv_order);
        codeList = roomDB.codeDAO().getAllByOrderID(orderId);
        orderAdapter = new OrderAdapter(codeList, this);
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

        if (order.getIsSynch() == 0) d_confirm.setText("Delete order without synchoronization?");
        else  d_confirm.setText("Delete order?");

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


    public void synchOrderWithServer(View view){
        SynchMan synchMan = new SynchMan(this);
        synchMan.synchOrder(order);
    }


    @Override
    public void onItemClick(Code code) {
        if (codeList.size() <= 1){
            showDialogAlert();
        } else {
            roomDB.codeDAO().delete(code);
            orderAdapter.notifyDataSetChanged();
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

        d_confirm.setText("You try to delete last code in order? After this order will delete automatically");

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