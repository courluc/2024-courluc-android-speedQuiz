package com.example.jeuquestion.Models;

import android.database.Cursor;
public class Question {

    private String sentence;
    private int result;

    public Question(Cursor cursor) {
        sentence = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        result = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }
    public String getSentence() { return sentence; }

    public int getResult() {
        return result;
    }


}