package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {

    TextView alreadylogin;
    Button Register;
    EditText username, phone, email, password;

    // Firebase Database Reference
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize views
        alreadylogin = findViewById(R.id.alreadybtn);
        Register = findViewById(R.id.button3);
        username = findViewById(R.id.editTextText);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        // Redirect to login if "Already have an account" is clicked
        alreadylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                startActivity(intent);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameText = username.getText().toString().trim();
                String phoneText = phone.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                // Validate input
                if (userNameText.isEmpty() || phoneText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(SignUp_Activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Show a progress dialog (optional)

                // Save data to Firebase
                User user = new User(userNameText, phoneText, emailText, passwordText);

                databaseReference.push().setValue(user).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp_Activity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                        // Start SignIn_Activity after successful registration
                        Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUp_Activity.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
