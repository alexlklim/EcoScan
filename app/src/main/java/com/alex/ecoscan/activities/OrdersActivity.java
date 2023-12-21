package com.alex.ecoscan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.alex.ecoscan.R;
import com.alex.ecoscan.adapters.OrdersAdapter;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.SynchMan;
import com.alex.ecoscan.model.Order;

import java.util.List;

public class OrdersActivity extends AppCompatActivity implements OrdersAdapter.OnItemClickListener{
    private static final String TAG = "OrdersActivity";
    Context context;
    RecyclerView recyclerView;
    OrdersAdapter orderAdapter;
    SynchMan synchMan;
    RoomDB roomDB;

    boolean showNotSynchOrders;
    SettingsMng settingsMng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // initialize main elements
        roomDB = RoomDB.getInstance(this);
        context = getApplicationContext();
        settingsMng = new SettingsMng(this);



        initializeRecyclerView();


        ImageView menu = findViewById(R.id.os_menu);
        menu.setOnClickListener(v -> showPopupMenu(menu));


    }


    @Override
    protected void onResume() {
        super.onResume();
        orderAdapter.notifyDataSetChanged();
        initializeRecyclerView();

    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.os_rv_orders);

        List<Order> orderList = roomDB.orderDAO().getAll();
        if(showNotSynchOrders) orderList.removeIf(order -> order.getIsSynch() == 1);

        orderAdapter = new OrdersAdapter(orderList, this, roomDB);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(orderAdapter);

    }



    @Override
    public void onItemClick(int orderId) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("ORDER_ID", orderId);
        startActivity(intent);
    }

    private void showPopupMenu(View view) {
        Log.i(TAG, "showPopupMenu: ");
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_orders, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId(); // Store the item ID in a final variable
            if (itemId == R.id.menu_synchOrders) {
//                synchWithServer();
                return true;
            } else if (itemId == R.id.menu_hideOrders) {
              showNotSynchOrders = true;
              initializeRecyclerView();
            }
            return false;
        });
        popupMenu.show();
    }


}