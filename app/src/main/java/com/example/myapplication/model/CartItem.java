package com.example.myapplication.model;

public class CartItem {
    private String name;
    private String price;
    private int quantity;
    private int imageResId;

    // Constructor
    public CartItem(String name, String price, int quantity, int imageResId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageResId = imageResId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for price
    public String getPrice() {
        return price;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Getter for image resource ID
    public int getImageResId() {
        return imageResId;
    }

    // Setter for quantity (if needed)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


