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
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.SynchMan;
import com.alex.ecoscan.managers.Tost;
import com.alex.ecoscan.model.Order;

import java.util.ArrayList;
import java.util.List;


public class DialogSentAllData extends AppCompatDialogFragment {
    private static final String TAG = "DialogSentAllData";

    SynchMan synchMan;
    RoomDB roomDB;

    SettingsMng settingsMng;
    private FragmentActivity fragmentActivity;  // Added reference to FragmentActivity

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm, null);
        FormatMng formatMng = new FormatMng();
        builder.setView(view).setTitle("Sent all data to server");
        settingsMng = new SettingsMng(requireContext());
        synchMan = new SynchMan(requireContext());
        roomDB = RoomDB.getInstance(requireContext());

        // Assign the FragmentActivity reference
        fragmentActivity = requireActivity();

        Button btn_yes = view.findViewById(R.id.btn_yes);
        Button btn_no = view.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(v -> {
            synch();
            dismiss();
        });

        btn_no.setOnClickListener(v -> {
            dismiss();
        });

        return builder.create();
    }

    private void synch() {
        List<Order> list = roomDB.orderDAO().getAll();

        synchMan.synchOrders(list, new SynchMan.OnSynchCompleteListener() {
            @Override
            public void onSynchComplete(int responseCode) {
                if (fragmentActivity != null) {  // Check if the activity is not null
                    fragmentActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Tost.show("All data was synch", fragmentActivity);
                        }
                    });
                }
            }
        });
    }
}
