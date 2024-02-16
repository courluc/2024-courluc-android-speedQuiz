package com.example.jeuquestion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jeuquestion.Controllers.GameManager;
import com.example.jeuquestion.Models.Question;
import com.google.android.material.button.MaterialButton;

public class gameActivity extends AppCompatActivity {
    private MaterialButton BT_questionPlayer1;
    private MaterialButton BT_questionPlayer2;
    private MaterialButton BT_restart;
    private MaterialButton BT_menu;
    private TextView TV_player1Points;
    private TextView TV_player2Points;
    private TextView TV_player1Name;
    private TextView TV_player2Name;
    private TextView TV_player1Question;
    private TextView TV_player2Question;
    private View RL_menuRestart;
    public String player1Name;
    public String player2Name;
    public int player1Points;
    public int player2Points;
    public boolean buttonPressed = false;
    public boolean firstClick = true;
    GameManager gameManager;
    public ArrayList<Question> questionList;
    private final long delay = 5000;
    Handler handler;
    Runnable questionRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //Initialisation des objets présent sur la page
        BT_questionPlayer1 = findViewById(R.id.bt_player1_question);
        BT_questionPlayer2 = findViewById(R.id.bt_player2_question);
        BT_restart = findViewById(R.id.btn_restart);
        BT_menu =findViewById(R.id.btn_menu);
        TV_player1Name = findViewById(R.id.player1_name);
        TV_player2Name = findViewById(R.id.player2_name);
        TV_player1Points = findViewById(R.id.view_points_player1);
        TV_player2Points = findViewById(R.id.view_points_player2);
        TV_player1Question = findViewById(R.id.text_view_player1_static);
        TV_player2Question = findViewById(R.id.text_view_player2_static);
        RL_menuRestart = findViewById(R.id.relativeLayout);

        //Récupération de l'instance crée à la page d'accueil
        Intent gameActivity = getIntent();
        player1Name = gameActivity.getStringExtra("player1Name");
        player2Name = gameActivity.getStringExtra("player2Name");
        gameManager = new GameManager(this);
        questionList = gameManager.getQuestions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Affichage du nom des joueurs
        TV_player1Name.setText(player1Name);
        TV_player2Name.setText(player2Name);

        //Mélange les questions et affiche la première
        gameManager.shuffleQuestions(questionList);
        startQuestionIterative();
        gameManager = new GameManager(this);

        BT_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retourne a la page d'accueil lorsque le bouton menu est cliqué
                finish();
            }
        });
        BT_questionPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ajoute des points au joueur 1 et affiche la question suivante
                if (!firstClick) {
                    player1Points = addPlayerPoints(player1Points, TV_player1Points);
                    buttonPressed = true;
                }
                firstClick = false;
                displayQuestion();
            }
        });
        BT_questionPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ajoute des points au joueur 2 et affiche la question suivante
                if (!firstClick){
                    player2Points = addPlayerPoints(player2Points, TV_player2Points);
                    buttonPressed = true;
                }
                firstClick = false;
                displayQuestion();
            }
        });
        BT_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Réinitialisation des points
                buttonPressed = false;
                player1Points = 0;
                player2Points = 0;
                gameManager.setIndex(0);

                TV_player1Points.setText(String.valueOf(player1Points));
                TV_player2Points.setText(String.valueOf(player2Points));

                //Affichage de la première question
                gameManager.shuffleQuestions(questionList);
                displayQuestion();

                //Rend les boutons actifs et rend les boutons "menu" et "restart" invisible
                BT_questionPlayer1.setEnabled(true);
                BT_questionPlayer2.setEnabled(true);
                RL_menuRestart.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * Affiche une nouvelle question toutes les 5 secondes
     */
    private void startQuestionIterative(){
        if (handler == null) {
            handler = new Handler();
        }
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if (gameManager.EndOfList()) {
                    addPoints();
                    //code de fin de partie
                    handler.removeCallbacks(this);
                    TV_player1Question.setText(R.string.end_of_game);
                    TV_player2Question.setText(R.string.end_of_game);
                    //Désactive les boutons des joueurs
                    BT_questionPlayer1.setEnabled(false);
                    BT_questionPlayer2.setEnabled(false);
                    //Affiche le layout contenant les boutons "menu" et "restart"
                    RL_menuRestart.setVisibility(View.VISIBLE);
                } else {
                    //code pour poser une question
                    addPoints();
                    buttonPressed = false;
                    String currentQuestion = gameManager.nextQuestion(questionList);
                    TV_player1Question.setText(currentQuestion);
                    TV_player2Question.setText(currentQuestion);
                    firstClick = false;
                    handler.postDelayed(this, delay);
                }
            }
        };
        handler.postDelayed(questionRunnable, delay);
    }

    /**
     * Affiche la question suivante si l'on est pas à la fin de la liste
     */
    public void displayQuestion(){
        if (!gameManager.EndOfList()) {
            String currentQuestion = gameManager.nextQuestion(questionList);
            TV_player1Question.setText(currentQuestion);
            TV_player2Question.setText(currentQuestion);
            buttonPressed = false;

        }else {
            TV_player1Question.setText(R.string.end_of_game);
            TV_player2Question.setText(R.string.end_of_game);
            //Désactive les boutons des joueurs
            BT_questionPlayer1.setEnabled(false);
            BT_questionPlayer2.setEnabled(false);
            //Affiche le layout contenant les boutons "menu" et "restart"
            RL_menuRestart.setVisibility(View.VISIBLE);
        }
        handler.removeCallbacks(questionRunnable);
        handler.postDelayed(questionRunnable, delay);

    }
    /**
     * Ajoute des points au joueur si la réponse est juste et en retire si elle est fausse
     * @param playerPoints points du joueur
     * @param TV_Player text view du joueur
     * @return le nombre du points du joueur mis à jour
     */
    public int addPlayerPoints(int playerPoints, TextView TV_Player){
        if (gameManager.getAnswer(questionList) == 1) {
            playerPoints++;
        }else if (playerPoints > 0){
            playerPoints--;
        }
        TV_Player.setText(String.valueOf(playerPoints));
        return playerPoints;
    }

    /**
     * Ajoute ou retire des points si personne n'a appuyé sur le bouton
     */
    public void addPoints(){
        //Test si l'index de la question est correct et si le bouton à été appuyé
        if (gameManager.getIndexQuestion() > 0 && !buttonPressed &&
            gameManager.getIndexQuestion() <= questionList.size()) {

            //Vérification de la réponse
            if (gameManager.getAnswer(questionList) == 0) {
                player1Points++;
                player2Points++;
            } else if (player2Points > 0 && player1Points > 0) {
                player1Points--;
                player2Points--;
            }
        }
        TV_player1Points.setText(String.valueOf(player1Points));
        TV_player2Points.setText(String.valueOf(player2Points));
    }
}
