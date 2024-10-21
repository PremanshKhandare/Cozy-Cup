package com.example.myapplication; // Make sure this matches your package name

public class User {
    public String username;
    public String phone;
    public String email;
    public String password;

    // Default constructor is required for Firebase
    public User() {
    }

    // Constructor with parameters
    public User(String username, String phone, String email, String password) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}

