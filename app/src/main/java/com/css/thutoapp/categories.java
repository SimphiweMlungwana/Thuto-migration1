package com.css.thutoapp;

public class categories {

    public categories(){

    }
    String quiz_name;
    int question;

    public String getQuiz_name() {
        return quiz_name;
    }

    public int getQuestion() {
        return question;
    }

    public categories(String quiz_name, int question) {
        this.quiz_name = quiz_name;
        this.question = question;
    }

}
