package com.alex.ecoscan.managers.network;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkCheckServerConnection extends AsyncTask<Void, Void, Integer> {

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