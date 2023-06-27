package com.css.thutoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<categories> list;

    public MyAdapter(Context context, ArrayList<categories> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        categories cat = list.get(position);

        holder.quiz_category.setText(cat.quiz_name);
        holder.no_of_quiz.setText(cat.question);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quiz_category, no_of_quiz;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quiz_category = itemView.findViewById(R.id.cat_txt);
            no_of_quiz = itemView.findViewById(R.id.no_of_quiz_txt);
        }
    }
}
