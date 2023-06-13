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

        Button mainactivitynxt = findViewById(R.id.btngoogle);

        mainactivitynxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mv) {
                Intent mIntent = new Intent(ActivityLogin.this, MainLandingActivity.class);
                startActivity(mIntent);
            }
        });
    }
}