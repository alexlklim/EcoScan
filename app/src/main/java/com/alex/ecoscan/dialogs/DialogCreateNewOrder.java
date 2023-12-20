package com.alex.ecoscan.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.alex.ecoscan.R;
import com.alex.ecoscan.activities.ScanActivity;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;

public class DialogCreateNewOrder extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_order, null);

        builder.setView(view).setTitle("Create new order");


        EditText d_orderNum = view.findViewById(R.id.d_orderNum);
        Button btnConfirm = view.findViewById(R.id.d_btn_confirm);

        btnConfirm.setOnClickListener(v -> {

            // check if this orderNum is unique in DB
            String orderNum = d_orderNum.getText().toString();

            if (orderNum.isEmpty()) {
                Tost.show("Something wrong", requireContext());
            } else if (RoomDB.getInstance(requireContext()).orderDAO().isExistByOrderNum(orderNum)) {
                // check is this orderNumber is exist in DB
                Tost.show("Order number already exists", requireContext());
            } else {
                Intent intent = new Intent(requireActivity(), ScanActivity.class);
                dismiss();
                intent.putExtra("ORDER_NUMBER", orderNum);
                startActivity(intent);
            }
            Tost.show("Something wrong", requireContext());
        });



        return builder.create();
    }
}
