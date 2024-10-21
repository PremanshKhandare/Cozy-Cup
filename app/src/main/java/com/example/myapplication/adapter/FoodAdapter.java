package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.FoodItem;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<FoodItem> foodItems;

    // Constructor for initializing context and the list
    public FoodAdapter(Context context, List<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout (buy_again_items) for each item
        View view = LayoutInflater.from(context).inflate(R.layout.buy_again_items, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        // Get the current food item
        FoodItem foodItem = foodItems.get(position);

        // Bind the data to the views
        holder.foodName.setText(foodItem.getName());
        holder.foodPrice.setText(foodItem.getPrice());
        holder.foodImage.setImageResource(foodItem.getImageResource());

        // Set click listener on the entire itemView (for the food item)
        holder.itemView.setOnClickListener(view -> {
            // Create an intent to start DetailsActivity
            Intent intent = new Intent(context, DetailsActivity.class);
            // Start the activity without passing any extra data
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    // Define the ViewHolder class for holding the views
    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodPrice;
        ImageView foodImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.textView16); // Corresponds to Food Name
            foodPrice = itemView.findViewById(R.id.textView17); // Corresponds to Food Price
            foodImage = itemView.findViewById(R.id.imageView6); // Corresponds to Image
        }
    }
}



