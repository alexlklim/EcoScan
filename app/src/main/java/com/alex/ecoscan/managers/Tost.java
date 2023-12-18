package com.alex.ecoscan.managers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class Tost {

    public static void show(String message, Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
    }
}
