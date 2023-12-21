package com.alex.ecoscan.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.ecoscan.R;
import com.alex.ecoscan.database.RoomDB;
import com.alex.ecoscan.managers.DateMng;
import com.alex.ecoscan.model.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private RoomDB roomDB;
    private OnItemClickListener onItemClickListener;
    // Constructor to initialize the orderList
    public OrdersAdapter(List<Order> orderList, OnItemClickListener onItemClickListener, RoomDB roomDB) {
        this.orderList = orderList;
        this.onItemClickListener = onItemClickListener;
        this.roomDB = roomDB;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_orders, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        // Bind order data to views in the ViewHolder
        holder.title.setText(order.getOrderNum());
        holder.date.setText(DateMng.extractHoursAndMinutes(order.getDate()));
        holder.amount.setText(String.valueOf(roomDB.codeDAO().getCodeCountForOrder(order.getID())));

        // Set the ImageView based on the isSynch value
        if (order.getIsSynch() == 1) {
            holder.isSynch.setImageResource(R.drawable.ic_success);
        } else {
            holder.isSynch.setImageResource(R.drawable.ic_fail);
        }

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(order.getID());
                animateItemClicked(holder.itemView);
            }
        });
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // ViewHolder class
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, amount;
        ImageView isSynch;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.r_title);
            date = itemView.findViewById(R.id.r_date);
            amount = itemView.findViewById(R.id.r_amount);
            isSynch = itemView.findViewById(R.id.r_isSynch);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int orderId);
    }


    private void animateItemClicked(View view) {
        int colorFrom = Color.argb(0, 255, 255, 255);
        int colorTo = Color.GRAY;

        // Duration for the initial animation
        long initialDuration = 300; // milliseconds

        // Delay before restoring the background color
        long delayBeforeRestore = 500; // milliseconds

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(initialDuration);

        colorAnimation.addUpdateListener(animator -> {
            view.setBackgroundColor((int) animator.getAnimatedValue());
        });

        colorAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Post a delayed action to restore the background color
                new Handler().postDelayed(() -> restoreBackgroundColor(view), delayBeforeRestore);
            }
        });

        colorAnimation.start();
    }

    private void restoreBackgroundColor(View view) {
        // Restore the background color to white
        int colorFrom = Color.GRAY;
        int colorTo = Color.argb(0, 255, 255, 255);

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(300); // milliseconds

        colorAnimation.addUpdateListener(animator -> {
            view.setBackgroundColor((int) animator.getAnimatedValue());
        });

        colorAnimation.start();
    }


}
