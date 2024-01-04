package com.alex.ecoscan.dialogs.settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.alex.ecoscan.R;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;

public class DialogChangeLogin extends AppCompatDialogFragment {

    SettingsMng settingsMng;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_login_pw, null);
        builder.setView(view).setTitle("Change login");
        settingsMng = new SettingsMng(requireContext());

        EditText d_login = view.findViewById(R.id.d_first);
        EditText d_pw = view.findViewById(R.id.d_second);

        d_login.setHint("new login");
        d_pw.setHint("password");
        Button btnConfirm = view.findViewById(R.id.d_btn_confirm);

        btnConfirm.setOnClickListener(v -> {
            String pwToCheck = d_pw.getText().toString();
            String pwCorrect = settingsMng.getPassword();
            if (pwToCheck.equals(pwCorrect)){
                settingsMng.setNewLogin(d_login.getText().toString());
                Tost.show("Login was changed", requireContext());
                dismiss();
            }
            Tost.show("Something wrong", requireContext());

        });



        return builder.create();
    }



}
