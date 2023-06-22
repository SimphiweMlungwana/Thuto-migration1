package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginScreenNxt = findViewById(R.id.btnLogin);
        Button registrationScreenNxt = findViewById(R.id.btnReg);
        TextView guestLoginText = findViewById(R.id.guestsignin_txt);
        loginScreenNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vln) {
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

        registrationScreenNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vrg) {
                Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });


    }
}