package com.alex.ecoscan.dialogs.settings;

import android.app.Dialog;
import android.os.Bundle;
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
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.managers.Tost;

public class DialogChangePw extends AppCompatDialogFragment {
    private static final String TAG = "DialogChangePw";

    SettingsMng settingsMng;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_login_pw, null);
        FormatMng formatMng = new FormatMng();
        builder.setView(view).setTitle("Change password");
        settingsMng = new SettingsMng(requireContext());

        TextView d_textChange = view.findViewById(R.id.d_textChange);
        EditText d_newPw = view.findViewById(R.id.d_first);
        EditText d_oldPw = view.findViewById(R.id.d_second);
        d_newPw.setHint("new password");
        d_oldPw.setHint("old password");
        d_textChange.setText("Change password");
        Button btnConfirm = view.findViewById(R.id.d_btn_confirm);

        btnConfirm.setOnClickListener(v -> {
            String pwNew = d_newPw.getText().toString();
            String pwOld = d_oldPw.getText().toString();
            String pwCorrect = settingsMng.getPassword();
            if (pwOld.equals(pwCorrect)){
                settingsMng.setNewPw(pwNew);
                Tost.show("Password was changed", requireContext());
                dismiss();
            }
            Tost.show("Something wrong", requireContext());

        });



        return builder.create();
    }

}
