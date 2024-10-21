package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.example.myapplication.model.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment implements CartAdapter.OnCartItemListener {

    private FragmentSearchBinding binding;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private List<CartItem> filteredCartItems;  // To store the filtered items

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the list of cart items
        cartItems = new ArrayList<>();
        filteredCartItems = new ArrayList<>();  // For search results
        populateCartItems();  // Populate with example data
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Initialize view binding
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Set up RecyclerView
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the CartAdapter with the filteredCartItems list and set the adapter to RecyclerView
        cartAdapter = new CartAdapter(getContext(), filteredCartItems, this);
        binding.menuRecyclerView.setAdapter(cartAdapter);

        // Set up the SearchView for filtering items
        setupSearchView();

        return view;
    }

    private void populateCartItems() {
        // Add some sample cart items (replace drawable with actual image resources)
        cartItems.add(new CartItem("Pancake", "Rs. 120.00", 1, R.drawable.pancake));
        cartItems.add(new CartItem("Sandwich", "Rs. 50.00", 1, R.drawable.sandwich));
        cartItems.add(new CartItem("Burger", "Rs. 79.00", 1, R.drawable.burger));
        cartItems.add(new CartItem("Pizza", "Rs. 110.00", 1, R.drawable.pizza));
        cartItems.add(new CartItem("Momos", "Rs. 100.00", 1, R.drawable.momos));
        cartItems.add(new CartItem("Noodles", "Rs. 150.00", 1, R.drawable.noodles));
        cartItems.add(new CartItem("Idli", "Rs. 80.00", 1, R.drawable.idli));
        cartItems.add(new CartItem("Pav Bhaji", "Rs. 90.00", 1, R.drawable.pavbhaji));
        cartItems.add(new CartItem("Garlic Bread", "Rs. 70.00", 1, R.drawable.garlicbread));
        cartItems.add(new CartItem("French Fries", "Rs. 60.00", 1, R.drawable.ffries));
        cartItems.add(new CartItem("Ice Cream", "Rs. 40.00", 1, R.drawable.icecream));

        // Initially show all items
        filteredCartItems.addAll(cartItems);
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);  // Call filter method when query is submitted
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);  // Call filter method when query is changed
                return true;
            }
        });
    }

    // Filter method to search items by name
    private void filter(String query) {
        filteredCartItems.clear();  // Clear the filtered list

        if (query.isEmpty()) {
            // If query is empty, show all items
            filteredCartItems.addAll(cartItems);
        } else {
            // Filter items based on the query (case-insensitive)
            for (CartItem item : cartItems) {
                if (item.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                    filteredCartItems.add(item);
                }
            }
        }

        // Notify the adapter to refresh the RecyclerView
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleted(int position) {
        // Handle item deletion from filteredCartItems
        filteredCartItems.remove(position);
        cartAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onQuantityIncreased(int position) {
        // Handle quantity increase in filteredCartItems
        CartItem item = filteredCartItems.get(position);
        item.setQuantity(item.getQuantity() + 1);
        cartAdapter.notifyItemChanged(position);
    }

    @Override
    public void onQuantityDecreased(int position) {
        // Handle quantity decrease in filteredCartItems
        CartItem item = filteredCartItems.get(position);
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;  // Clean up binding to prevent memory leaks
    }
}



