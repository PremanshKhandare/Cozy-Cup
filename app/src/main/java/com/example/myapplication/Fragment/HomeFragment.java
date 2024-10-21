package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.MenuBottomSheetFragment;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PopularAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.PopularItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // Declare view binding object
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Initialize View Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Initialize image slider
        setupImageSlider();

        // Initialize RecyclerView
        setupRecyclerView();

        // Set up click listener for "View Menu" TextView
        binding.textView12.setOnClickListener(v -> showBottomSheetDialog());

        // Return the root view of the binding (i.e., the layout's root)
        return binding.getRoot();

    }

    private void setupImageSlider() {
        // Initialize image list for the image slider
        ArrayList<SlideModel> imageList = new ArrayList<>();

        // Add drawable images to the list
        imageList.add(new SlideModel(R.drawable.food1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.food7, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.food3, ScaleTypes.FIT));

        // Set the image list to the slider using view binding
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT);

        // Set ItemClickListener to handle clicks on individual slides
        binding.imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int i) {
                // Handle double click event (if needed)
            }

            @Override
            public void onItemSelected(int position) {
                // Handle click event based on the position of the clicked item
                switch (position) {
                    case 0:
                        Toast.makeText(getContext(), "You clicked on Delicious Food 1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "You clicked on Tasty Food 2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(), "You clicked on Yummy Food 3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void setupRecyclerView() {
        // Initialize the food list for the RecyclerView
        List<PopularItem> foodList = getPopularItems();

        // Set layout manager and adapter for RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PopularAdapter adapter = new PopularAdapter(getContext(), foodList);
        binding.recyclerView.setAdapter(adapter);
    }

    // Sample method to create the list of popular items
    private List<PopularItem> getPopularItems() {
        List<PopularItem> items = new ArrayList<>();
        items.add(new PopularItem("Pancake", R.drawable.pancake, "Rs. 120.00"));
        items.add(new PopularItem("Sandwich", R.drawable.sandwich, "Rs. 50.00"));
        items.add(new PopularItem("Momos", R.drawable.momos, "Rs. 100.00"));
        items.add(new PopularItem("Noodles", R.drawable.noodles, "Rs. 150.00"));
        items.add(new PopularItem("Idli", R.drawable.idli, "Rs. 80.00"));
        items.add(new PopularItem("Pav Bhaji", R.drawable.pavbhaji, "Rs. 90.00"));
        return items;
    }

    private void showBottomSheetDialog() {
        // Create an instance of the BottomSheetDialogFragment
        MenuBottomSheetFragment bottomSheetFragment = new MenuBottomSheetFragment();

        // Show the bottom sheet
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set binding to null to avoid memory leaks
        binding = null;
    }
}




