package com.css.thutoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class QuizScreenActivity extends AppCompatActivity {

    private TextView questionTV,questionNumberTV;
    private Button option1btn,option2btn,option3btn,option4btn;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0, questionAttempted = 0, currentPos = 0;

    private FirebaseFirestore db;
    private CollectionReference collectionRef;


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

        db = FirebaseFirestore.getInstance();
        collectionRef = db.collection("QuestionList");

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
                    //s
                    showSuccessBTS();
                }else{
                    showUnsuccessfulBTS();
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
                    //s
                    showSuccessBTS();
                }else{
                    showUnsuccessfulBTS();
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
                    //s
                    showSuccessBTS();
                }else{
                    showUnsuccessfulBTS();
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
                    //s
                    showSuccessBTS();
                }else{
                    showUnsuccessfulBTS();
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                sendDataToViews(currentPos);
            }
        });
    }

    private void showUnsuccessfulBTS() {
        //create an unsuccessfull bottomsheet here
        BottomSheetDialog unsucBTSDialog = new BottomSheetDialog(QuizScreenActivity.this);
        View unBTSView = LayoutInflater.from(getApplicationContext()).
                inflate(R.layout.dialog_incorrect_answ,(RelativeLayout)findViewById(R.id.IncorrectAnsLayout));

        Button dismissViewUnBtn = unBTSView.findViewById(R.id.btn_dismiss_view_inc);
        TextView questionTV = unBTSView.findViewById(R.id.txt_quiz);
        TextView correctAnsTV = unBTSView.findViewById(R.id.crct_dyn_txt);
        TextView descriptionTV = unBTSView.findViewById(R.id.crct_dyn_descript_txt);

        questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
        correctAnsTV.setText(quizModalArrayList.get(currentPos).getAnswer());
        descriptionTV.setText(quizModalArrayList.get(currentPos).getDescription());

        dismissViewUnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unsucBTSDialog.dismiss();
            }
        });
        unsucBTSDialog.setCancelable(false);
        unsucBTSDialog.setContentView(unBTSView);
        unsucBTSDialog.show();
    }

    private void showSuccessBTS() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QuizScreenActivity.this);
        View btsView = LayoutInflater.from(getApplicationContext()).
                inflate(R.layout.dialog_correct_answer,(RelativeLayout)findViewById(R.id.correctAnsLayout));
        Button dismissViewBtn = btsView.findViewById(R.id.btn_dismiss_view);

        dismissViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //questionAttempted++;

                bottomSheetDialog.dismiss();

                //currentPos = random.nextInt(quizModalArrayList.size());
                //sendDataToViews(currentPos);
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(btsView);
        bottomSheetDialog.show();
    }


    private void sendDataToViews(int currentPos) {
        questionNumberTV.setText("Question: "+questionAttempted +"/5\nScore: "+currentScore);
        if(questionAttempted==5){
            sendResultsData();
            }else{
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4btn.setText(quizModalArrayList.get(currentPos).getOption4());
        }

    }

    private void sendResultsData() {
        String currentScore1 = String.valueOf(currentScore);
        String attemptedquiz1 = String.valueOf(questionAttempted);
        String percentcompl = String.valueOf((currentScore/5)*100);
        String correct_answ = String.valueOf(currentScore/10);

        Intent qtorintent = new Intent(QuizScreenActivity.this, ResultsActivity.class);

        qtorintent.putExtra(ResultsActivity.currentScore,currentScore1);
        qtorintent.putExtra(String.valueOf(ResultsActivity.attemptedquiz),attemptedquiz1);
        qtorintent.putExtra(ResultsActivity.percentcompl,percentcompl);
        qtorintent.putExtra(ResultsActivity.correct_answ,correct_answ);

        startActivity(qtorintent);
    }

    private void getQuizQuestions(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Which of the following sports is Africa known for?", "Cricket",
                "Rugby","Football","Basketball","Football",
                "Football or soccer has been known in Africa for over 200 years, and it is the most popular game on the continent. Football requires only a single ball to be played, hence it is very simple to play, and in almost village in Africa you may see the kids enjoying football. It is no surprise that the 2010 FIFA world cup was held in South Africa. The appeal of football to Africans has led to many African athletes rising to fame through soccer."));
        quizModalArrayList.add(new QuizModal("Which of the following African countries hosted the FIFA world cup in 2010?",
                "Senegal","Cameroon","South Africa","Ghana","South Africa",
                "The 2010 FIFA World Cup was the 19th FIFA World Cup, the world championship for men's national association football teams. It took place in South Africa from 11 June to 11 July 2010. The bidding process for hosting the tournament finals was open only to African nations."));
        quizModalArrayList.add(new QuizModal("Which of the following African countries is best in Athletics?",
                "Botswana","South Africa","Kenya","Ethiopia","Kenya",
                "Kenya is known for producing world-class track stars and Kenyan athletes are so dominant in the whole world"));
        quizModalArrayList.add(new QuizModal("Which of the following African sprinters has been dubbed the next Usain Bolt?",
                "Trayvon Bromells","Letsile Tebogo","Blessing Akwasi","Bouwahjgie Nkrumie","Letsile Tebogo",
                "Nineteen-year-old Letsile Tebogo of Botswana has been dubbed the next Usain Bolt. In April, Tebogo broke Trayvon Bromell’s under-20 world record for the 100m, which had stood for eight years. He clocked 9.96 seconds."));
        quizModalArrayList.add(new QuizModal("Which one of the following won Miss Universe 2019 and one of the things she is passionate\n" +
                "about is teaching young girls to ‘take up space'?",
                "Catriona Gray","Shudufhadzo Musida","Zozibini Tunzi","Mpule Kwelagobe","Zozibini Tunzi",
                "Zozibini Tunzi is a South African model and winner of the beauty pageant “Miss Universe 2019.” One of her famous lines is, “I think we are the most powerful beings in the world and that we should be given every opportunity and that is what we should be teaching these young girls, to take up space, nothing is as important as taking up space in society and cementing yourself”."));

        quizModalArrayList.add(new QuizModal("Which of the following African countries has more women in Parliament?",
                "Tanzania","Cabo Verde","Senegal","Rwanda","Rwanda",
                "Over 60 percent of the seats in Rwanda's national parliament were held by women as of May 2022. The country had the strongest female participation in politics in Africa. It also ranked first globally, with the highest proportion of women in power in lower houses than in any other nation."));
        quizModalArrayList.add(new QuizModal("Which of the following African feminists from Nigeria authored the book ‘ Purple Hibiscus’ and is best known for her themes of politics, culture, race, and gender?",
                "Gloria Steimen","Filomina Chioma Steady","Chimamanda Ngozi Adichie","Abena Busia","Chimamanda Ngozi Adichie",
                "Chimamanda Ngozi Adichie is a writer and storyteller, best known for her themes of politics, culture, race, and gender. Her novels, short stories, and plays have all received both public and critical acclaim."));
        quizModalArrayList.add(new QuizModal("Which of the following universities is ranked first in Africa?",
                "University of South Africa","University of Zambia","University of Cape Town","Cairo University","University of Cape Town",
                "The number 1 university in Africa is the University of Cape Town"));
        quizModalArrayList.add(new QuizModal("Which of the following is NOT one of the worst performing currencies in Africa ’22?",
                "Nigerian Naira","Zimbabwean dollar","Sudanese pounds","Zambian Kwacha","Zambian Kwacha",
                "The Zambian kwacha is one of the world's best performing currencies against the US dollar"));
        quizModalArrayList.add(new QuizModal("_______ is the most populous country in Africa",
                "Tanzania","Ethiopia","Kenya","Nigeria","Nigeria",
                "Nigeria is the most populous country in Africa, followed by Ethiopia and Egypt"));

        quizModalArrayList.add(new QuizModal("Which African country is well known for Nollywood?",
                "Nigeria","India","South Africa","Ghana","Nigeria",
                "Nigeria produced over 2,500 films in 2020. With Nollywood, its booming film industry, the country recorded the largest movie production in Africa"));
        quizModalArrayList.add(new QuizModal("Which sector employs most people in Africa?",
                "Mining","Information and Technology","Agriculture","Infrastructure","Agriculture",
                "In Africa, agriculture has remained a leading sector for decades. Whether its economic activities around farming or the market for agricultural products, investment opportunities are plenty. Agriculture remains the sector employing most people in Africa. It is projected to account for. 50.5 per cent of all employment in 2020."));
        quizModalArrayList.add(new QuizModal("Which of the following African entrepreneurs was named one of Forbes 30 Most Promising Young African Entrepreneurs in 2017?",
                "Patrice Motsepe","Jean Bosco Nzeyimana","Jason Njoku","Lorna Rutto","Jean Bosco Nzeyimana",
                " Jean Bosco Nzeyimana was named one of Forbes 30 Most Promising Young Entrepreneurs in Africa in 2017"));
        quizModalArrayList.add(new QuizModal("Which of the following African countries is the first to host the Forbes Under 30 Summit Africa?",
                "South Africa","Botswana","Tunisia","Cote d’Ivoire","Botswana",
                "Forbes held its first-ever Under 30 Summit Africa in Botswana on April 24-29 2022"));
        quizModalArrayList.add(new QuizModal("_____ is considered the most innovative African country",
                "Senegal","South Africa","Mauritius","Gabon","Mauritius",
                "Mauritius ranks first in Africa in Global Innovation Index 2022. According to the report, Mauritius emerged the most innovative African country, and climbing seven places to 45th on the Global Innovation Index (GII). Unlike most African countries, the report said Mauritius has a high human development score"));
    }


}