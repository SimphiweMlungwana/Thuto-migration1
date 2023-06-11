package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button homefragmentnxt = findViewById(R.id.btngoogle);

        homefragmentnxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vhm) {
                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}