package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.NotificationAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Arrays;
import java.util.List;

public class Notification_Bottom_Fragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification__bottom_, container, false);

        // Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // List of notifications
        List<String> notifications = Arrays.asList(
                "Order cancelled successfully",
                "Driver has picked up your order",
                "Congrats your order has been placed"
        );

        // Dummy images list (replace with actual drawable resources)
        List<Integer> images = Arrays.asList(
                R.drawable.order,  // Replace with actual drawable
                R.drawable.deliveryman,     // Replace with actual drawable
                R.drawable.clapping    // Replace with actual drawable
        );

        // Set adapter for RecyclerView
        NotificationAdapter adapter = new NotificationAdapter(notifications, images);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
