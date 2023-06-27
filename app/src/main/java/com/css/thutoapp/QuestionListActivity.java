package com.css.thutoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QuestionListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<categories> cat_list;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        recyclerView = findViewById(R.id.listQuizRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        cat_list = new ArrayList<categories>();
        myAdapter = new MyAdapter(QuestionListActivity.this,cat_list);

        recyclerView.setAdapter(myAdapter);
        
        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("QuizList").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    if ((progressDialog.isShowing()))
                        progressDialog.dismiss();
                    Log.e("Internal Error",error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        cat_list.add(dc.getDocument().toObject(categories.class));
                    }
                    myAdapter.notifyDataSetChanged();
                    if ((progressDialog.isShowing()))
                        progressDialog.dismiss();
                }
            }
        });
    }
}