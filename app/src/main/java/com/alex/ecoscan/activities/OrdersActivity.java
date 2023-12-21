package com.alex.ecoscan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.adapters.OrdersAdapter;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.SynchMan;
import com.alex.ecoscan.model.Order;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

//        sentRequest();

        new SendRequestTask().execute();
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


    private class SendRequestTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            // Use OkHttpClient singleton
            OkHttpClient client = new OkHttpClient();

            String url = "https://webhook.site/cf20be59-150b-491c-a279-7912cf18c78e";

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.code();
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., log or notify the user)
                e.printStackTrace();
                return -1; // Use a sentinel value for error
            }
        }

        @Override
        protected void onPostExecute(Integer statusCode) {
            // Process the result on the main thread (update UI or perform other actions)
            System.out.println("!!!!!!!!!!!!!!!!!");
            System.out.println("CALL");

            // Check the status code
            if (statusCode == 200) {
                System.out.println("Request was successful. Status code: " + statusCode);
                // Handle the success case
            } else {
                System.out.println("Request failed. Status code: " + statusCode);
                // Handle other status codes if needed
            }
        }
    }


// To execute the task:




}