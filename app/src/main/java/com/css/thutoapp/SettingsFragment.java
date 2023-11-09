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
import android.widget.Toast;

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
                try {
                    if(isChecked){
                        mediaPlayer.start();
                    }else {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                }catch (NullPointerException e){
                    Toast.makeText(getActivity(), "No music playing!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;
    }
}