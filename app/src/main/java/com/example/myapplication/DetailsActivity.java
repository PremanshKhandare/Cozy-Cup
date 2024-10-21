package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.CartItem;
import com.example.myapplication.utils.CartManager;

public class DetailsActivity extends AppCompatActivity {

    private TextView foodNameTextView, descriptionTextView, ingredientsTextView;
    private ImageView foodImageView;
    private Button addToCartButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialize views
        foodNameTextView = findViewById(R.id.detailFoodName);
        descriptionTextView = findViewById(R.id.DescriptionTextView);
        ingredientsTextView = findViewById(R.id.IngredientTextView);
        foodImageView = findViewById(R.id.detailFoodImage);
        addToCartButton = findViewById(R.id.button2);
        backButton = findViewById(R.id.imageButton);

        // Dummy food data (this can be passed from previous activity or fetched dynamically)
        String foodName = "Pancake";
        String foodPrice = "Rs. 120.00";
        int foodImageRes = R.drawable.pancake;

        // Set food data to views
        foodNameTextView.setText(foodName);
        descriptionTextView.setText("Delicious pancakes with syrup.");
        ingredientsTextView.setText("1st item\n2nd item\n3rd item");
        foodImageView.setImageResource(foodImageRes);

        // Set "Add to Cart" button listener
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new CartItem and add to CartManager
                CartItem cartItem = new CartItem(foodName, foodPrice, 1, foodImageRes);
                CartManager.getInstance().addItemToCart(cartItem);

                // Display a message or navigate to CartFragment
                Toast.makeText(DetailsActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle back button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close the current activity and return to the previous screen
                finish();
            }
        });
    }
}
