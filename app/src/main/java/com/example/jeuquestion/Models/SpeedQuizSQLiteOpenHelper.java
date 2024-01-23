package com.example.jeuquestion.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME="SpeedQuiz.db";
    static int DB_VERSION=1;

    public SpeedQuizSQLiteOpenHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}