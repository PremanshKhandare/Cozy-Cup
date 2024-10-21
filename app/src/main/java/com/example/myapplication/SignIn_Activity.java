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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn_Activity extends AppCompatActivity {

    TextView alreadylogin;
    Button SignIn;
    EditText emailField, passwordField;

    // Firebase authentication instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        alreadylogin = findViewById(R.id.alreadybtn);
        SignIn = findViewById(R.id.button3);
        emailField = findViewById(R.id.editTextTextEmailAddress);
        passwordField = findViewById(R.id.editTextTextPassword);

        // Navigate to SignUp_Activity when "Want to create an Account?" is clicked
        alreadylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

        // Sign in when the Sign In button is clicked
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });
    }

    // Method to sign in the user using Firebase Authentication
    private void signInUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // Sign in fails
                            Toast.makeText(SignIn_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        } else {
            Toast.makeText(SignIn_Activity.this, "Email and password cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to update the UI after successful sign-in
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Navigate to the next activity after successful login
            Intent intent = new Intent(SignIn_Activity.this, ChooseLocation.class);
            startActivity(intent);
            finish();
        }
    }
}
