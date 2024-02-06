package com.example.jeuquestion.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedGameSqLite extends SQLiteOpenHelper {

    static String DB_NAME = "SpeedGame.db";
    static int DB_VERSION = 1;


    public SpeedGameSqLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDataTableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY, question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDataTableQuiz);

        db.execSQL("INSERT INTO quiz VALUES (1, \"Le plus grand océan de la Terre est l'Océan Atlantique\", 1)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"La Tour Eiffel a été construite au 17e siècle\", 0)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"William Shakespeare était un dramaturge français\", 0)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"Le Mont Everest est la plus haute montagne du monde\", 1)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"Le panda est un membre de la famille des félins\", 0)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"La Mona Lisa a été peinte par Vincent van Gogh\", 0)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"La Grande Muraille de Chine est visible depuis la Lune\", 0)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"La devise des États-Unis est E Pluribus Unum\", 0)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"Le Kilimandjaro est le plus haut sommet d'Afrique\", 1)");
        db.execSQL("INSERT INTO quiz VALUES (null, \"La guerre de Cent Ans a duré plus de 100 ans\", 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
