package com.css.thutoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment{

    RelativeLayout relativeLayout;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = rootView.findViewById(R.id.cat_pic1);
        relativeLayout = rootView.findViewById(R.id.category_block);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment detailFragment = new DetailFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.container,detailFragment).
                        commit();
            }
        });
        return rootView;
    }


}