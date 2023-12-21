package com.alex.ecoscan.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.alex.ecoscan.MainActivity;
import com.alex.ecoscan.R;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;

public class DialogResultOfSavingOrder extends AppCompatDialogFragment {

    private static final String TAG = "DialogCompleteOrder";
    SettingsMng settingsMng;
    private String orderNum;

    public DialogResultOfSavingOrder(String orderNum) {
        this.orderNum = orderNum;
    }
    public DialogResultOfSavingOrder() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_result, null);
        builder.setView(view).setTitle("Create new order");

        RoomDB roomDB = RoomDB.getInstance(requireContext());


        TextView d_text_result = view.findViewById(R.id.d_text_result);
        ImageView d_image_result = view.findViewById(R.id.d_save_result);
        Button btnOkay = view.findViewById(R.id.d_btn_okay);
        if (roomDB.orderDAO().isExistByOrderNum(orderNum)){
            d_text_result.setText("Order was saved successfully");
            d_image_result.setImageResource(R.drawable.ic_success);

            Tost.show("Success", requireContext());
        } else{
            d_text_result.setText("Something wrong");
            d_image_result.setImageResource(R.drawable.ic_fail);
            Tost.show("Something wrong", requireContext());
        }


        btnOkay.setOnClickListener(v -> {
            Intent intent  = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            dismiss();
            requireActivity().finish();
//            Intent intent  = new Intent(requireActivity(), MainActivity.class);
//            startActivity(intent);
        });



        return builder.create();
    }
}
