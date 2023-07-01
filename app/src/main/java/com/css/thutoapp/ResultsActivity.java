package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    Button btnRepeatQuiz;
    TextView correctAnsTV, incorrectAnsTV, completPercTV, pointAccum;

    //QuizScreenActivity quizScreenActivity = new QuizScreenActivity();
    Intent QuizActIntent = getIntent();
    String currentScore = QuizActIntent.getStringExtra("score");
    int attemptedquiz = QuizActIntent.getIntExtra("attemptedquizes",0);
    double percentcompl = QuizActIntent.getDoubleExtra("percentcomplete",0);
    int correct_answ = QuizActIntent.getIntExtra("correct_ans",0);
    int incorrectans = 5-correct_answ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        btnRepeatQuiz = findViewById(R.id.btn_take_newquiz);

        correctAnsTV = findViewById(R.id.correct_txt);
        incorrectAnsTV = findViewById(R.id.incorrect_txt);
        completPercTV = findViewById(R.id.completion_perc_txt);
        pointAccum = findViewById(R.id.no_of_points_txt);

        pointAccum.setText("+"+currentScore+" points");
        correctAnsTV.setText(correct_answ);
        incorrectAnsTV.setText(incorrectans);
        completPercTV.setText((int) percentcompl);



        btnRepeatQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rtoqintent = new Intent(ResultsActivity.this, QuizScreenActivity.class);
                startActivity(rtoqintent);
            }
        });
    }
}