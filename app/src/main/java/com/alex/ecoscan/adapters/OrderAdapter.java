package com.alex.ecoscan.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.managers.DateMng;
import com.alex.ecoscan.managers.SettingsMng;
import com.alex.ecoscan.model.Code;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Code> codeList;

    private OnItemClickListener onItemClickListener;
    static boolean isAllowEdit;

    public OrderAdapter(List<Code> codeList, OnItemClickListener onItemClickListener, boolean isAllowEdit) {
        this.codeList = codeList;
        this.onItemClickListener = onItemClickListener;
        this.isAllowEdit = isAllowEdit;
    }

    public boolean newCodeListAfterDeleting(Code code) {
        codeList.remove(code);
        if (codeList.isEmpty()) {
            return false;
        }
        return true;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Code code = codeList.get(position);

        holder.tv_code.setText(code.getCode());
        holder.tv_time.setText(DateMng.extractHoursAndMinutes(code.getTime()));

        if (code.getGpsLat().equalsIgnoreCase("none") || code.getGpsLon().equalsIgnoreCase("none"))
            holder.iv_gps.setImageResource(R.drawable.ic_gps_not);
        else holder.iv_gps.setImageResource(R.drawable.ic_gps);

        holder.btn_del.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(code);
            }
        });
        // Handle clicks or other interactions here if needed
    }

    @Override
    public int getItemCount() {
        return codeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_code, tv_time;
        ImageView iv_gps;
        ImageButton btn_del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_code = itemView.findViewById(R.id.ro_code);
            tv_time = itemView.findViewById(R.id.ro_time);
            iv_gps = itemView.findViewById(R.id.ro_gps);
            btn_del = itemView.findViewById(R.id.ro_delete);
            if (!isAllowEdit){
                btn_del.setVisibility(View.INVISIBLE);
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Code code);
    }
}
