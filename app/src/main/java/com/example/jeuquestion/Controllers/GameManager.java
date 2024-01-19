package com.example.jeuquestion.Controllers;
import com.example.jeuquestion.Models.speedQuizSQLiteOpenHelper;
import com.example.jeuquestion.Models.Question;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    private static GameManager instance;
    private speedQuizSQLiteOpenHelper dbHelper;
    private GameManager() {
        dbHelper = new speedQuizSQLiteOpenHelper();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public ArrayList<Question> getQuestions() {
        //Retourne la liste de questions
        return (ArrayList<Question>) dbHelper.getQuestions();
    }
    public void shuffleQuestions() {
        //Mélange les questions
        Collections.shuffle(getQuestions());
    }

//    public boolean getAnswer() {
//        return dbHelper.getQuestions().;
//    }
}

