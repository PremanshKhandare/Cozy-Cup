package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.POST;

public interface YourApiService {
    @POST("create-payment-intent") // Your backend endpoint
    Call<PaymentIntentResponse> createPaymentIntent();
}

