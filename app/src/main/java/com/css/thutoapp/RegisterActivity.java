package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etxtmail = findViewById(R.id.edTextEmail);
        etxtpassword = findViewById(R.id.edTextPassword);
    }
}