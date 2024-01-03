package com.alex.ecoscan.managers;

import android.content.Context;

import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.interfaces.ISynchMng;
import com.alex.ecoscan.model.Code;
import com.alex.ecoscan.model.Order;
import com.alex.ecoscan.model.OrderWithCodes;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SynchMan implements ISynchMng {
    private static final String TAG = "SynchMan";
    private static Context context;
    RoomDB roomDB;
    SettingsMng sm;
    URL url;

    public SynchMan(Context context) {
        SynchMan.context = context;
    }


    @Override
    public void synchOrders(List<Order> orders) {
        if (!checkCommonCases()){
            return;
        }
        roomDB = RoomDB.getInstance(context);

        List<OrderWithCodes> orderWithCodesList = new ArrayList<>();
        for (Order order : orders) {
            List<Code> codes = roomDB.codeDAO().getAllByOrderID(order.getID());
            // Populate the list of codes for each order with your data
            orderWithCodesList.add(order.toOrderWithCodes(codes));
        }
        Gson gson = new Gson();
        String json = gson.toJson(orderWithCodesList);

        System.out.println(json);

        sendPostRequestAsync(sm.getServerAddress(), json, new OnPostRequestCompleteListener() {
            @Override
            public void onPostRequestComplete(int responseCode) {
                System.out.println("Response Code: " + responseCode);
                // Check if the status code is 200 (OK)
                if (responseCode == 200) {
                    System.out.println("Request successful");
                    changeToSynchOrders(orders);
                } else {
                    System.out.println("Request failed");
                    Tost.show("Synchronization failed", context);
                }
            }
        });


    }



    @Override
    public boolean checkCommonCases() {
        // server is not configured
        sm = new SettingsMng(context);
        if (!sm.isServerConfigured()){
            Tost.show("Server is not configured", context);
            return false;
        }
        // no internet connection
        if (!NetworkMng.isNetworkAvailable(context)){
            Tost.show("No internet connection", context);
            return false;
        }



        return true;
    }



    @Override
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
