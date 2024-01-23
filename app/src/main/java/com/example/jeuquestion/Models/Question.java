package com.example.jeuquestion.Models;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
public class Question {

    private String intitule;
    private int reponses;

    public Question(String intitule, int reponses) {
        this.intitule = intitule;
        this.reponses = reponses;
    }

    public Question(Cursor cursor) {
        intitule = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponses = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    public String getIntitule() {
        return intitule;
    }

    public int getReponses() {
        return reponses;
    }


}