package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.model.CartItem;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment implements CartAdapter.OnCartItemListener {

    private List<CartItem> cartItems = new ArrayList<>();
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_bottom_sheet, container, false);

        // Set up RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Back button click listener
        ImageButton backButton = view.findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Create and set adapter with listener
        cartAdapter = new CartAdapter(getContext(), cartItems, this);
        recyclerView.setAdapter(cartAdapter);

        // Populate cart items
        populateCartItems();

        return view;
    }


    private void populateCartItems() {
        cartItems.add(new CartItem("Pancake", "Rs. 120.00", 1, R.drawable.pancake));
        cartItems.add(new CartItem("Sandwich", "Rs. 50.00", 1, R.drawable.sandwich));
        cartItems.add(new CartItem("Burger", "Rs. 79.00", 1, R.drawable.burger));
        cartItems.add(new CartItem("Pizza", "Rs. 110.00", 1, R.drawable.pizza));
        cartItems.add(new CartItem("Momos", "Rs. 100.00", 1, R.drawable.momos));
        cartItems.add(new CartItem("Noodles", "Rs. 150.00", 1, R.drawable.noodles));
        cartItems.add(new CartItem("Idli", "Rs. 80.00", 1, R.drawable.idli));
        cartItems.add(new CartItem("Pav Bhaji", "Rs. 90.00", 1, R.drawable.pavbhaji));
        cartItems.add(new CartItem("Garlic Bread", "Rs. 70.00", 1, R.drawable.garlicbread));
        cartItems.add(new CartItem("French Fries", "Rs. 60.00", 1, R.drawable.ffries));
        cartItems.add(new CartItem("Ice Cream", "Rs. 40.00", 1, R.drawable.icecream));
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleted(int position) {
        cartItems.remove(position);
        cartAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onQuantityIncreased(int position) {
        CartItem item = cartItems.get(position);
        item.setQuantity(item.getQuantity() + 1);
        cartAdapter.notifyItemChanged(position);
    }

    @Override
    public void onQuantityDecreased(int position) {
        CartItem item = cartItems.get(position);
        item.setQuantity(item.getQuantity() - 1);
        cartAdapter.notifyItemChanged(position);
    }
}


