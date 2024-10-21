package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FoodAdapter;
import com.example.myapplication.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<FoodItem> foodItemList;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.HistoryRecyclerView);

        // Set LayoutManager (Linear or Grid)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the food list and add sample items
        foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItem("Burger", "Rs 79.00", R.drawable.burger));
        foodItemList.add(new FoodItem("Pizza", "Rs 110.00", R.drawable.pizza));
        foodItemList.add(new FoodItem("Sandwich", "Rs 50.00", R.drawable.sandwich));
        foodItemList.add(new FoodItem("Noodles", "Rs 150.00", R.drawable.noodles));
        foodItemList.add(new FoodItem("Ice Cream", "Rs 40.00", R.drawable.icecream));
        // Add more items as needed

        // Set the adapter
        adapter = new FoodAdapter(getContext(), foodItemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
