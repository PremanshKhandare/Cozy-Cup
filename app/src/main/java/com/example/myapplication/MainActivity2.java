package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Find the NavHostFragment and get the NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        // Find the BottomNavigationView and set it up with NavController
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView3);
        NavigationUI.setupWithNavController(bottomNav, navController);

        // Find the ImageView (bell icon) and set an OnClickListener
        ImageView bellIcon = findViewById(R.id.imageView3);
        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the NotificationBottomFragment when the bell icon is clicked
                Notification_Bottom_Fragment notificationBottomFragment = new Notification_Bottom_Fragment();
                notificationBottomFragment.show(getSupportFragmentManager(), "Notification_Bottom_Fragment");
            }
        });
    }
}

