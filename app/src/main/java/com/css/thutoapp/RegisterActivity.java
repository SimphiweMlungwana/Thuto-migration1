package com.css.thutoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText email,passwrd;
    private Button signupbtn;
    private TextView logindirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.TextEmail);
        passwrd = findViewById(R.id.TextPassword);
        logindirect = findViewById(R.id.guestlogin_txt);
        signupbtn = findViewById(R.id.buttonRegister);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString().trim();
                String pass = passwrd.getText().toString().trim();

                if(user.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Email empty", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password empty", Toast.LENGTH_SHORT).show();
                }else {
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,ActivityLogin.class));
                            }else {
                                Toast.makeText(RegisterActivity.this, "Registration failed: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        logindirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,ActivityLogin.class));
            }
        });
    }
}