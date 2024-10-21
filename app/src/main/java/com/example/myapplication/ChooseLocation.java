package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityChooseLocationBinding;

public class ChooseLocation extends AppCompatActivity {

    ActivityChooseLocationBinding binding;
    Button Proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChooseLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] cities = {"Mumbai", "Delhi", "Jaipur", "Bengaluru", "Chennai"};

        binding.textViewSelectedCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCityDialog(cities);
            }
        });
    }

    // Method to show the city selection dialog
    private void showCityDialog(String[] cities) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a City");

        // Set the array of cities in the dialog
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Update the TextView with the selected city
                String selectedCity = cities[which];
                binding.textViewSelectedCity.setText("Selected City: " + selectedCity);
            }
        });

        // Show the dialog
        builder.show();

        Proceed = findViewById(R.id.button4);

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseLocation.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        };



    }
