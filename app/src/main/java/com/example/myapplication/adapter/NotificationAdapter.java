package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<String> notificationList;
    private List<Integer> imageList;

    public NotificationAdapter(List<String> notificationList, List<Integer> imageList) {
        this.notificationList = notificationList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        String notification = notificationList.get(position);
        int imageRes = imageList.get(position);

        holder.bind(notification, imageRes);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView notificationTextView;
        private ImageView notificationImageView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationTextView = itemView.findViewById(R.id.notification_text);
            notificationImageView = itemView.findViewById(R.id.notification_image);
        }

        public void bind(String notification, int imageRes) {
            notificationTextView.setText(notification);
            notificationImageView.setImageResource(imageRes);
        }
    }
}


