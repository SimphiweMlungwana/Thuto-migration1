package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static String currentScore;
    public static String attemptedquiz;
    public static String percentcompl;
    public static String correct_answ;
    public static String incorrectans;
    Button btnRepeatQuiz;
    TextView correctAnsTV, incorrectAnsTV, completPercTV, pointAccum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        btnRepeatQuiz = findViewById(R.id.btn_take_newquiz);

        Intent QIntent = getIntent();
        currentScore = QIntent.getStringExtra(currentScore);
        attemptedquiz = QIntent.getStringExtra(attemptedquiz);
        percentcompl = QIntent.getStringExtra(percentcompl);
        correct_answ = QIntent.getStringExtra(correct_answ);

        correctAnsTV = findViewById(R.id.correct_txt);
        incorrectAnsTV = findViewById(R.id.incorrect_txt);
        completPercTV = findViewById(R.id.completion_perc_txt);
        pointAccum = findViewById(R.id.no_of_points_txt);

        pointAccum.setText("+"+currentScore+" points");
        correctAnsTV.setText(correct_answ);
        incorrectAnsTV.setText(incorrectans);
        completPercTV.setText(percentcompl+"%");



        btnRepeatQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rtoqintent = new Intent(ResultsActivity.this, QuizScreenActivity.class);
                startActivity(rtoqintent);
            }
        });
    }
}