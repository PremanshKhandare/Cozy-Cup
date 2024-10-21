package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final List<CartItem> cartItems;
    private final OnCartItemListener onCartItemListener; // Listener for cart item actions

    // Constructor to pass context, list of cart items, and the listener
    public CartAdapter(Context context, List<CartItem> cartItems, OnCartItemListener onCartItemListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.onCartItemListener = onCartItemListener;
    }

    // Define the interface for handling item interactions
    public interface OnCartItemListener {
        void onItemDeleted(int position);
        void onQuantityIncreased(int position);
        void onQuantityDecreased(int position);
    }

    // ViewHolder class to hold the layout for each cart item
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImageView;
        TextView foodNameTextView;
        TextView priceTextView;
        TextView quantityTextView;
        ImageButton minusButton;
        ImageButton plusButton;
        ImageButton deleteButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.cartimage);
            foodNameTextView = itemView.findViewById(R.id.cartfoodname);
            priceTextView = itemView.findViewById(R.id.cartprice);
            quantityTextView = itemView.findViewById(R.id.quantity);
            minusButton = itemView.findViewById(R.id.minusbtn);
            plusButton = itemView.findViewById(R.id.plusbtn);
            deleteButton = itemView.findViewById(R.id.deletebtn);
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout for each cart item
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get the current cart item
        CartItem item = cartItems.get(position);

        // Set the cart item details using the getter methods
        holder.foodNameTextView.setText(item.getName());
        holder.priceTextView.setText(item.getPrice());
        holder.foodImageView.setImageResource(item.getImageResId());
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

        // Set up the plus and minus button listeners for changing quantity
        holder.minusButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                onCartItemListener.onQuantityDecreased(position);
            }
        });

        holder.plusButton.setOnClickListener(v -> onCartItemListener.onQuantityIncreased(position));

        // Set up the delete button listener for removing the item from the cart
        holder.deleteButton.setOnClickListener(v -> onCartItemListener.onItemDeleted(position));
    }

    @Override
    public int getItemCount() {
        // Return the size of the cart items list
        return cartItems.size();
    }
}


