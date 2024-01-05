package com.alex.ecoscan.dialogs.datamanagement;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.alex.ecoscan.R;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;

public class DialogConfirmDelSynchData extends AppCompatDialogFragment {
    private static final String TAG = "DialogConfirmDelSynchData";

    RoomDB roomDB;
    SettingsMng settingsMng;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm, null);
        builder.setView(view).setTitle(R.string.delete_all_synch_data);
        settingsMng = new SettingsMng(requireContext());
        roomDB = RoomDB.getInstance(requireContext());
        FragmentActivity fragmentActivity = requireActivity();

        Button btn_yes = view.findViewById(R.id.btn_yes);
        Button btn_no = view.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(v -> {
            roomDB.orderDAO().deleteAllSynch();
            Tost.show(getString(R.string.all_synch_orders_was_deleted), requireContext());
            dismiss();
            dismiss();
        });

        btn_no.setOnClickListener(v -> {

            dismiss();
        });

        return builder.create();
    }
}
