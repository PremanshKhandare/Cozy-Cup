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
import com.example.myapplication.DetailsActivity; // Ensure this import is correct
import com.example.myapplication.R;
import com.example.myapplication.model.PopularItem;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private final Context context;
    private final List<PopularItem> popularItems;

    // Constructor to pass context and list of items
    public PopularAdapter(Context context, List<PopularItem> popularItems) {
        this.context = context;
        this.popularItems = popularItems;
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemPrice; // Added for price display

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.imageView4);
            itemName = itemView.findViewById(R.id.popularfood);
            itemPrice = itemView.findViewById(R.id.pricepopular); // Initialize the price TextView
        }
    }


    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout for each item
        View view = LayoutInflater.from(context).inflate(R.layout.popularitems, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        // Get the current item
        PopularItem currentItem = popularItems.get(position);

        // Bind the data with the views
        holder.itemName.setText(currentItem.getName());
        holder.itemImage.setImageResource(currentItem.getImageResId());
        holder.itemPrice.setText(currentItem.getPrice()); // Set the price here

        // Set an OnClickListener to open the DetailsActivity
        holder.itemView.setOnClickListener(v -> {
            // Create an intent to start DetailsActivity
            Intent intent = new Intent(context, DetailsActivity.class);

            // Pass the item's name, image resource ID, and price to the next activity
            intent.putExtra("name", currentItem.getName());
            intent.putExtra("imageResId", currentItem.getImageResId());
            intent.putExtra("price", currentItem.getPrice()); // Pass the price as well

            // Start the activity
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        // Return the size of the list
        return popularItems.size();
    }
}

