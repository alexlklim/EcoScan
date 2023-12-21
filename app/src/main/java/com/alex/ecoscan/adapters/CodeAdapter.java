package com.alex.ecoscan.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.model.Code;

import java.util.List;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.CodeViewHolder> {

    private List<Code> codeList;
    private OnItemClickListener onItemClickListener;

    public CodeAdapter(List<Code> codeList, OnItemClickListener onItemClickListener) {
        this.codeList = codeList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_scan, parent, false);
        return new CodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeViewHolder holder, int position) {
        Code code = codeList.get(position);
        // set values to rv
        holder.code.setText("Code: " + code.getCode());
        holder.label.setText("Label: " + code.getLabel());
    }

    @Override
    public int getItemCount() {
        return codeList.size();
    }

    public static class CodeViewHolder extends RecyclerView.ViewHolder {
        public TextView code, label;

        public CodeViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.r_code);
            label = itemView.findViewById(R.id.r_label);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Code code);
    }

}
