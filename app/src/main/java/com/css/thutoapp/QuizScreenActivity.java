package com.css.thutoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuizScreenActivity extends AppCompatActivity {

    private TextView questionTV,questionNumberTV;
    private Button option1btn,option2btn,option3btn,option4btn;
    private ArrayList<QuizModal> quizModalArrayList;

    Random random;
    int currentScore = 0, questionAttempted = 1, currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        questionTV = findViewById(R.id.txt_quiz);
        questionNumberTV = findViewById(R.id.question_attempted);
        option1btn = findViewById(R.id.answ1);
        option2btn = findViewById(R.id.answ2);
        option3btn = findViewById(R.id.answ3);
        option4btn = findViewById(R.id.answ4);

        quizModalArrayList = new ArrayList<>();

        random = new Random();
        getQuizQuestions(quizModalArrayList);

        currentPos = random.nextInt(quizModalArrayList.size());
        sendDataToViews(currentPos);
        
        option1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().
                        equals(option1btn.getText().toString().trim().toLowerCase())){
                    currentScore = currentScore+10;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                sendDataToViews(currentPos);
            }
        });

        option2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().
                        equals(option2btn.getText().toString().trim().toLowerCase())){
                    currentScore = currentScore+10;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                sendDataToViews(currentPos);
            }
        });

        option3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().
                        equals(option3btn.getText().toString().trim().toLowerCase())){
                    currentScore = currentScore+10;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                sendDataToViews(currentPos);
            }
        });

        option4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().
                        equals(option4btn.getText().toString().trim().toLowerCase())){
                    currentScore = currentScore+10;

                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                sendDataToViews(currentPos);
            }
        });
    }

    private void showDialog(){
        Dialog dialog = new Dialog(this);
    }
    private void sendDataToViews(int currentPos) {
        questionNumberTV.setText("Question: "+questionAttempted +"/5");
        if(questionAttempted==5){
            Intent qtorintent = new Intent(QuizScreenActivity.this, ResultsActivity.class);
            qtorintent.putExtra("score",currentScore);
            qtorintent.putExtra("correct_ans",currentScore/10);
            qtorintent.putExtra("attemptedquizes",questionAttempted);
            qtorintent.putExtra("percentcomplete",(questionAttempted/5)*100);
            startActivity(qtorintent);
        }else{
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4btn.setText(quizModalArrayList.get(currentPos).getOption4());
        }

    }

    private void getQuizQuestions(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Which of the following sports is Africa known for?", "Cricket",
                "Rugby","Football","Basketball","Football"));
        quizModalArrayList.add(new QuizModal("Which of the following African countries hosted the FIFA world cup in 2010?",
                "Senegal","Cameroon","South Africa","Ghana","South Africa"));
        quizModalArrayList.add(new QuizModal(" Which of the following African countries is best in Athletics?",
                "Botswana","South Africa","Kenya","Ethiopia","Kenya"));
        quizModalArrayList.add(new QuizModal("Which of the following African sprinters has been dubbed the next Usain Bolt?",
                "Trayvon Bromells","Letsile Tebogo","Blessing Akwasi","Bouwahjgie Nkrumie","Letsile Tebogo"));
        quizModalArrayList.add(new QuizModal(" Which one of the following won Miss Universe 2019 and one of the things she is passionate\n" +
                "about is teaching young girls to ‘take up space'?",
                "Catriona Gray","Shudufhadzo Musida","Zozibini Tunzi","Mpule Kwelagobe","Zozibini Tunzi"));
    }
}