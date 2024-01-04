package com.alex.ecoscan.managers;

import android.content.Context;

import com.alex.ecoscan.R;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.model.Code;
import com.alex.ecoscan.model.Order;
import com.alex.ecoscan.model.OrderWithCodes;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SynchMan  {
    private static Context context;
    RoomDB roomDB;
    SettingsMng sm;

    public SynchMan(Context context) {
        SynchMan.context = context;
    }

    @FunctionalInterface
    public interface OnSynchCompleteListener {
        void onSynchComplete(int responseCode);
    }



    public void synchOrders(List<Order> orders, OnSynchCompleteListener synchCompleteListener) {
        if (!checkCommonCases()) return;
        roomDB = RoomDB.getInstance(context);
        List<OrderWithCodes> orderWithCodesList = new ArrayList<>();
        for (Order order : orders) {
            List<Code> codes = roomDB.codeDAO().getAllByOrderID(order.getID());
            orderWithCodesList.add(order.toOrderWithCodes(codes));
        }

        Gson gson = new Gson();
        String json = gson.toJson(orderWithCodesList);
        sendPostRequestAsync(sm.getServerAddress(), json, responseCode -> {
            if (responseCode == 200) changeToSynchOrders(orders);
            else Tost.show("Synchronization failed", context);
            // Notify the caller that the synch operation is complete
            synchCompleteListener.onSynchComplete(responseCode);
        });
    }



     public boolean checkCommonCases() {
        // server is not configured
        sm = new SettingsMng(context);
        if (!sm.isServerConfigured()){
            Tost.show(context.getString(R.string.server_is_not_configured), context);
            return false;
        }
        // no internet connection
        if (!NetworkMng.isNetworkAvailable(context)){
            Tost.show("No internet connection", context);
            return false;
        }



        return true;
    }




    public void changeToSynchOrders(List<Order> list) {
        for (Order order: list){
            order.setIsSynch(1);
            roomDB.orderDAO().update(order);
        }

    }




    public interface OnPostRequestCompleteListener {
        void onPostRequestComplete(int responseCode);
    }

    public static void sendPostRequestAsync(String url, String json, OnPostRequestCompleteListener listener) {
        new Thread(() -> {
            int responseCode = sendPostRequest(url, json);
            listener.onPostRequestComplete(responseCode);
        }).start();
    }

    private static int sendPostRequest(String url, String json) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            con.setRequestMethod("POST");

            // Set the request headers
            con.setRequestProperty("Content-Type", "application/json");

            // Enable input/output streams
            con.setDoOutput(true);

            // Write the JSON payload to the output stream
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(json);
                wr.flush();
            }

            // Get the response code
            int responseCode = con.getResponseCode();

            // Close the connection
            con.disconnect();

            return responseCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 to indicate an error
        }
    }





}
