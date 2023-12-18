package com.alex.ecoscan.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.alex.ecoscan.R;
import com.alex.ecoscan.activities.ScanActivity;
import com.alex.ecoscan.database.RoomDB;

public class DialogMng {
    private static final String TAG = "DialogMng";

    static AlertDialog alertDialog;


    public static void inputOrderNum(Activity activity, Context context) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_input_confirm, null);
        EditText orderNum = dialog.findViewById(R.id.d_orderNum);
        Button btnConfirm = dialog.findViewById(R.id.d_btn_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialog);

        btnConfirm.setOnClickListener(view -> {
            Log.d(TAG, "inputOrderNum dialog is open");
            String orderNumber = orderNum.getText().toString();
            if (orderNumber.isEmpty()){
                Log.e(TAG, "inputOrderNum: orderNumber is empty");
            } else if (RoomDB.getInstance(context).orderDAO().isExistByOrderNum(orderNumber)){
                Log.e(TAG, "inputOrderNum: orderNum already exists in DB");
            } else {
                Log.d(TAG, "inputOrderNum: " + orderNumber);
                Intent intent = new Intent(activity, ScanActivity.class);
                alertDialog.dismiss();
                intent.putExtra("ORDER_NUM", orderNumber);
                activity.startActivity(intent);
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

}
