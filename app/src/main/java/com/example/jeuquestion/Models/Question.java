package com.example.jeuquestion.Models;

public class Question {
    private String question;
    private boolean reponse; // true pour "oui", false pour "non"

    public Question(String question, boolean reponse) {
        this.question = question;
        this.reponse = reponse;
    }
    public String getQuestion() {
        return question;
    }

    public boolean getReponse() {
        return reponse;
    }


}
