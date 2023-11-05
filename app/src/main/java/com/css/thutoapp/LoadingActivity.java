package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private static final int s_time_out = 6000; //6 secs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mediaPlayer = MediaPlayer.create(this,R.raw.lofi_bacgrnd_music);
        mediaPlayer.start();
        //splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this, QuizScreenActivity.class));
                finish();
            }
        },s_time_out);
    }

}