package com.alex.ecoscan.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.alex.ecoscan.R;
import com.alex.ecoscan.activities.ScanActivity;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.model.Code;

import java.util.List;

public class DialogMng {
    private static final String TAG = "DialogMng";

    static AlertDialog alertDialog;
    static SettingsMng settingsMng;


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

    public static void confirmCompleteScan (Activity activity, Context context, RoomDB roomDB,
                                            String orderNumber, List<Code> codeList) {
        Log.d(TAG, "confirmCompleteScan: ");
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_confirm, null);

        TextView textConfirm = dialog.findViewById(R.id.d_confirm);
        textConfirm.setText(R.string.d_complete_order);
        Button btnYes = dialog.findViewById(R.id.d_btn_yes);
        Button btnNo = dialog.findViewById(R.id.d_btn_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialog);
        settingsMng = new SettingsMng(context);

        btnYes.setOnClickListener(view -> {

            if (codeList.isEmpty()){
                Tost.show(context.getString(R.string.t_empty_list), context);

            } else {
                boolean result = DatabaseMng.saveNewOrder(roomDB, orderNumber, codeList);
                if (result) {
                    Tost.show(context.getString(R.string.t_save_order_success), context);
                    if (settingsMng.isSentData() && settingsMng.isAutoSynch()){
                        // sent new order to server
                    }
                } else {
                    Tost.show(context.getString(R.string.t_save_order_fail), context);

                }
                alertDialog.dismiss();
                resultOfSavingOrder(activity, context, result);
            }

        });
        btnNo.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog = builder.create();
        alertDialog.show();
    }

    private static void resultOfSavingOrder(Activity activity, Context context, boolean result) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_result, null);
        TextView textResult = dialog.findViewById(R.id.d_text_result);
        ImageView imageResult = dialog.findViewById(R.id.d_save_result);
        Button btnOkay = dialog.findViewById(R.id.d_btn_okay);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(dialog);

        if (result){
            textResult.setText(context.getString(R.string.t_save_order_success));
            imageResult.setImageResource(R.drawable.ic_success);
        } else {
            textResult.setText(context.getString(R.string.t_save_order_fail));
            imageResult.setImageResource(R.drawable.ic_fail);
        }
        btnOkay.setOnClickListener(v -> {
            alertDialog.dismiss();
            activity.finish();
        });
        alertDialog = builder.create();
        alertDialog.show();

    }

}
