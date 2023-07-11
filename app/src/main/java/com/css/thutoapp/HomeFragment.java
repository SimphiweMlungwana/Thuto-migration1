package com.css.thutoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class HomeFragment extends Fragment{

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    RelativeLayout relativeLayout;
    ImageView imageView;
    TextView uname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = rootView.findViewById(R.id.cat_pic1);
        relativeLayout = rootView.findViewById(R.id.category_block);
        uname = rootView.findViewById(R.id.usersname);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        String userId = null;
        if (currentUser != null) {
            userId = currentUser.getUid();

            db.collection("users").document(userId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String userName = documentSnapshot.getString("firstname");
                            uname.setText(userName.toUpperCase());
                        }
                    })
                    .addOnFailureListener(e -> {
                        //Toast.makeText(HomeFragment.this,"Error!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    });
        } else {
            uname.setText("Guest!");
        }
        uname.setText(userId.toUpperCase());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (currentUser != null) {
                auth.signOut();
            }
        }));

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment detailFragment = new DetailFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, detailFragment).
                        commit();
            }
        });
        return rootView;
    }


}