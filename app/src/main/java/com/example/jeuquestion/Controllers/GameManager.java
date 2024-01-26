package com.example.jeuquestion.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.os.Handler;

import com.example.jeuquestion.Models.Question;
import com.example.jeuquestion.Models.SpeedGameSqLite;

import java.util.ArrayList;
import java.util.Collections;

public class GameManager {

    private static GameManager instance;
    public SpeedGameSqLite dbHelper;
    private int indexQuestion = 0;
    Context context;
    public GameManager(Context context) {
         dbHelper = new SpeedGameSqLite(context);
        this.context = context;
    }


    public static GameManager getInstance(Context context) {
        if (instance == null) {
            instance = new GameManager(context);
        }
        return instance;
    }

    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedGameSqLite helper = new SpeedGameSqLite(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);

        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return listQuestion;
    }

    public ArrayList<Question> getQuestions() {
        //Retourne la liste de questions
        return initQuestionList(context);
    }
    public void shuffleQuestions() {
        //Mélange les questions
        Collections.shuffle(getQuestions());
    }

    public int getAnswer() {
        return getQuestions().get(indexQuestion-1).getReponses();
    }

    public void setIndex(int index) {
        this.indexQuestion = index;
    }

    public String nextQuestion() {
        indexQuestion++;
        return initQuestionList(context).get(indexQuestion-1).getIntitule();
    }

    public boolean EndOfList(){
        return indexQuestion >= getQuestions().size();
    }

}

