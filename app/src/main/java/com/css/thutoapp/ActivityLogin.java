package com.css.thutoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText loginMail, loginPass;
    private Button loginBtn;

    HomeFragment homeFragment = new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginMail = findViewById(R.id.inpEmail);
        loginPass = findViewById(R.id.inppass);
        loginBtn = findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginMail.getText().toString();
                String pass = loginPass.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(ActivityLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ActivityLogin.this, MainLandingActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ActivityLogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (email.isEmpty()) {
                        loginMail.setError("Email cannot empty");
                    }else {
                        loginMail.setError("Enter correct mail");
                    }
                }
            }
        });
    }
}