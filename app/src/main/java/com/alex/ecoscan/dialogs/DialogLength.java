package com.alex.ecoscan.dialogs;

import android.app.Dialog;
import android.content.Context;
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
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;

public class DialogLength extends AppCompatDialogFragment {

    private EditText etLength, etLengthMin, etLengthMax;
    private DialogLengthListener dialogLengthListener;
    SettingsMng settingsMng;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_length, null);
        FormatMng formatMng = new FormatMng();
        builder.setView(view).setTitle("Length");
        settingsMng = new SettingsMng(requireContext());

        Button btnLength = view.findViewById(R.id.set_btn_length);
        btnLength.setOnClickListener(v -> {
            int length = formatMng.parseIntFromStringOrDefaultZero(etLength.getText().toString());
            int lengthMin = formatMng.parseIntFromStringOrDefaultZero(etLengthMin.getText().toString());
            int lengthMax = formatMng.parseIntFromStringOrDefaultZero(etLengthMax.getText().toString());
            dialogLengthListener.applyLengthConfig(length, lengthMin, lengthMax);
            dismiss();
        });

        etLength = view.findViewById(R.id.set_length);
        etLengthMin = view.findViewById(R.id.set_lengthMin);
        etLengthMax = view.findViewById(R.id.set_lengthMax);

        if (settingsMng.getLength() != 0) etLength.setText(String.valueOf(settingsMng.getLength()));
        if (settingsMng.getLengthMIN() != 0) etLengthMin.setText(String.valueOf(settingsMng.getLengthMIN()));
        if (settingsMng.getLengthMAX() != 0) etLengthMax.setText(String.valueOf(settingsMng.getLengthMAX()));

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogLengthListener = (DialogLengthListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "must implement DialogLengthListener");
        }
    }

    public  interface DialogLengthListener{
        void applyLengthConfig(int length, int lengthMin, int lengthMax);
    }
}
