package com.alex.ecoscan.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.alex.ecoscan.R;
import com.alex.ecoscan.managers.FormatMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.model.enums.Label;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DialogAdvancedFilter extends AppCompatDialogFragment {
    SettingsMng settingsMng;
    private EditText set_prefix, set_suffix, set_ending;
    TextView set_labelList;
    ImageView set_btn_clearLabelList;
    Spinner sLabels;
    DialogAdvancedFilterListener dialogAdvancedFilterListener;
    List<String> labelFilterList;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_advanced_filter, null);
        builder.setView(view).setTitle("Advanced Filter");
        FormatMng formatMng = new FormatMng();
        settingsMng = new SettingsMng(requireContext());
        labelFilterList = new ArrayList<>(settingsMng.getLabels());
        set_labelList = view.findViewById(R.id.set_labelList);
        if (settingsMng.getLabels().isEmpty()) set_labelList.setText(R.string.all_labels_are_accepted);
        else set_labelList.setText(new ArrayList<>(settingsMng.getLabels())
                .stream().collect(Collectors.joining(", ")));


        set_prefix = view.findViewById(R.id.set_prefix);
        set_suffix = view.findViewById(R.id.set_suffix);
        set_ending = view.findViewById(R.id.set_ending);

        set_prefix.setText(settingsMng.getPrefix());
        set_suffix.setText(settingsMng.getSuffix());
        set_ending.setText(settingsMng.getEnding());

        if (!settingsMng.getPrefix().equals("")) set_prefix.setText(String.valueOf(settingsMng.getPrefix()));
        if (!settingsMng.getSuffix().equals("")) set_suffix.setText(String.valueOf(settingsMng.getSuffix()));
        if (!settingsMng.getEnding().equals("")) set_ending.setText(String.valueOf(settingsMng.getEnding()));

        initializeLabelsSection(view);



        Button btnAdvancedFilter = view.findViewById(R.id.set_btn_advancedFilter);
        btnAdvancedFilter.setOnClickListener(v -> {
            String prefix = set_prefix.getText().toString();
            String suffix = set_suffix.getText().toString();
            String ending = set_ending.getText().toString();
            HashSet<String> labels = new HashSet<>(labelFilterList);
            dialogAdvancedFilterListener.applyAdvancedFilterConfig(prefix, suffix, ending, labels);
            dismiss();
        });

        set_btn_clearLabelList = view.findViewById(R.id.set_btn_clearLabelList);
        set_btn_clearLabelList.setOnClickListener(v -> {
            labelFilterList.clear();
            set_labelList.setText(R.string.all_labels_are_accepted);
        });

        return builder.create();

    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogAdvancedFilterListener = (DialogAdvancedFilterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "must implement DialogAdvancedFilterListener");
        }
    }



    public interface DialogAdvancedFilterListener{
        void applyAdvancedFilterConfig(String prefix, String suffix, String ending, HashSet<String> labels);
    }


    private void initializeLabelsSection(View view) {
        // initialize labels section to have possibility to make multiple choice
        sLabels = view.findViewById(R.id.set_labels);
        List<String> labelList = Label.getListLabels();
        ArrayAdapter<String> adapterLabelType = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, new ArrayList<>(labelList));
        adapterLabelType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLabels.setAdapter(adapterLabelType);
        sLabels.setSelection(0);
        sLabels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedLabel = (String) parentView.getItemAtPosition(position);
                if (!selectedLabel.equals(Label.NONE.getLabelName()) && !labelFilterList.contains(selectedLabel)){
                    labelFilterList.add(selectedLabel);
                    set_labelList.setText(labelFilterList.stream().collect(Collectors.joining(", ")));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
