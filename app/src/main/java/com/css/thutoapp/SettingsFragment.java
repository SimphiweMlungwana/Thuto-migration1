package com.css.thutoapp;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class SettingsFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private Switch aSwitchMusic;
    RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        aSwitchMusic = rootView.findViewById(R.id.SoundswtchBtn);
        //mediaPlayer = MediaPlayer.create(this,R.raw.lofi_bacgrnd_music);

        aSwitchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mediaPlayer.setVolume(0.8f,0.8f);
                    mediaPlayer.start();
                }else {
                    mediaPlayer.release();
                    mediaPlayer = null;
                    mediaPlayer.setVolume(0.0f,0.0f);
                }
            }
        });
        return rootView;
    }
}