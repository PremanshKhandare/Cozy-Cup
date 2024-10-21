package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.annotations.NotNull;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PayOutActivity extends AppCompatActivity {

    private Stripe stripe;
    private CardInputWidget cardInputWidget;
    private String clientSecret; // To hold the client secret returned from your backend

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_out);

        // Initialize Stripe SDK with your Publishable Key
        PaymentConfiguration.init(getApplicationContext(), "pk_test_51Q8IwfJd02xa5SnTYSpOm51pwoZMnEdQBDzKsQgDXkERxQ4DEp9TCR5BMqNshQF7isluON48WpXRqPXO6OuwpMnQ00KpRh1Clg"); // Replace with your publishable key
        stripe = new Stripe(getApplicationContext(), PaymentConfiguration.getInstance(this).getPublishableKey());

        // Initialize the CardInputWidget
        cardInputWidget = findViewById(R.id.cardInputWidget);

        // Find back button and set onClickListener
        ImageButton backButton = findViewById(R.id.button5);
        backButton.setOnClickListener(view -> finish());

        // Find place order button and set onClickListener
        Button placeOrderButton = findViewById(R.id.PayButton);
        placeOrderButton.setOnClickListener(view -> createPaymentIntent());
    }

    private void createPaymentIntent() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.123:3000/") // Your backend URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YourApiService apiService = retrofit.create(YourApiService.class);
        Call<PaymentIntentResponse> call = apiService.createPaymentIntent();
        call.enqueue(new Callback<PaymentIntentResponse>() {
            @Override
            public void onResponse(Call<PaymentIntentResponse> call, Response<PaymentIntentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    clientSecret = response.body().getClientSecret(); // Adjust according to your response
                    confirmPayment(clientSecret);
                } else {
                    Toast.makeText(PayOutActivity.this, "Failed to create PaymentIntent", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentIntentResponse> call, Throwable t) {
                Toast.makeText(PayOutActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmPayment(String clientSecret) {
        PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();

        if (params != null) {
            ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                    .createWithPaymentMethodCreateParams(params, clientSecret);

            // Confirm the payment
            stripe.confirmPayment(this, confirmParams);
        } else {
            Toast.makeText(this, "Invalid card data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new ApiResultCallback<PaymentIntentResult>() {
            @Override
            public void onSuccess(PaymentIntentResult result) {
                // Handle successful payment
                if (result.getIntent().getStatus() == PaymentIntent.Status.Succeeded) {
                    Toast.makeText(PayOutActivity.this, "Payment completed successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PayOutActivity.this, "Payment failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(@NotNull Exception e) {
                // Handle error
                Toast.makeText(PayOutActivity.this, "Payment error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
