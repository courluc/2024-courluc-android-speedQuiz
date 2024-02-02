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

    /**
     * Récupere la liste de questions dans la base de données
     * @param context context
     * @return la liste de question
     */
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

    /**
     * Reprend la liste de questions
     * @return la liste de questions
     */
    public ArrayList<Question> getQuestions() {
        return initQuestionList(context);
    }

    /**
     * Mélange une liste de questions
     * @param questionList liste de questions à mélanger
     */
    public void shuffleQuestions(ArrayList<Question> questionList) {
        Collections.shuffle(questionList);
        //Mélange les questions
    }

    /**
     *Retrouve la réponse d'une question
     * @param questionList liste de questions
     * @return la réponse
     */
    public int getAnswer(ArrayList<Question> questionList) {
        return questionList.get(indexQuestion-1).getReponses();
    }

    /**
     * Remet l'index à une valeur donnée
     * @param index index donné
     */
    public void setIndex(int index) {
        this.indexQuestion = index;
    }

    /**
     *Renvoie l'index de la question en cours
     * @return index de la question en cours
     */
    public int getIndexQuestion(){
        return indexQuestion;
    }

    /**
     * Récupere une question par rapport à un index
     * @param questionList liste de questions
     * @return la question à l'index en cours
     */
    public String nextQuestion(ArrayList<Question> questionList) {
        indexQuestion++;
        return questionList.get(indexQuestion-1).getIntitule();
    }

    /**
     * Vérifie si l'on est à la fin de la liste
     * @return retourne si oui ou non nous sommes à la fin de la liste
     */
    public boolean EndOfList(){
        return indexQuestion >= getQuestions().size();
    }

}

