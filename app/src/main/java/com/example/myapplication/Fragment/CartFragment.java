package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PayOutActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.utils.CartManager;

public class CartFragment extends Fragment implements CartAdapter.OnCartItemListener {

    private CartAdapter cartAdapter;
    private RecyclerView cartRecyclerView;
    private Button proceedBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize RecyclerView and Button
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        proceedBtn = view.findViewById(R.id.prdbtn);

        // Set up RecyclerView layout manager and adapter
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(getContext(), CartManager.getInstance().getCartItems(), this);
        cartRecyclerView.setAdapter(cartAdapter);

        // Set click listener for the proceed button
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle proceed to payment
                Intent intent = new Intent(getActivity(), PayOutActivity.class);  // Use getActivity() to get the context
                startActivity(intent);
            }
        });


        return view;
    }

    // Listener methods
    @Override
    public void onItemDeleted(int position) {
        CartManager.getInstance().getCartItems().remove(position);
        cartAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onQuantityIncreased(int position) {
        CartItem item = CartManager.getInstance().getCartItems().get(position);
        item.setQuantity(item.getQuantity() + 1);
        cartAdapter.notifyItemChanged(position);
    }

    @Override
    public void onQuantityDecreased(int position) {
        CartItem item = CartManager.getInstance().getCartItems().get(position);
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartAdapter.notifyItemChanged(position);
        }
    }
}
