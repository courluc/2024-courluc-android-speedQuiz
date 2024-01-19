package com.example.jeuquestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.List;
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
    public int indexQuestion = 0;
    public List<Question> questionList;
    private String currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //Récupération des questions
        questionList = GameManager.getInstance().getQuestions();

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

        //Récupère l'instance crée à la page d'accueil
        Intent gameActivity = getIntent();
        player1Name = gameActivity.getStringExtra("player1Name");
        player2Name = gameActivity.getStringExtra("player2Name");
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Affichage du nom des joueurs
        TV_player1Name.setText(player1Name);
        TV_player2Name.setText(player2Name);

        //Mélange les questions et affiche la première
        GameManager.getInstance().shuffleQuestions();
        randomQuestions();

        //Retourne a la page d'accueil lorsque le bouton menu est cliqué
        BT_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BT_questionPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ajoute des points au joueur 1 et affiche la question suivante
                player1Points++;
                TV_player1Points.setText(String.valueOf(player1Points));
                randomQuestions();
            }
        });
        BT_questionPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ajoute des points au joueur 2 et affiche la question suivante
                player2Points++;
                TV_player2Points.setText(String.valueOf(player2Points));
                randomQuestions();
            }
        });
        BT_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Réinitialise les points
                player1Points *= 0;
                player2Points *=0;
                indexQuestion *=0;
                TV_player1Points.setText(String.valueOf(player1Points));
                TV_player2Points.setText(String.valueOf(player2Points));
                //Affiche la première question
                randomQuestions();

                //Rend les boutons actifs et rend les boutons "menu" et "restart" invisible
                BT_questionPlayer1.setEnabled(true);
                BT_questionPlayer2.setEnabled(true);
                RL_menuRestart.setVisibility(View.INVISIBLE);
                //Mélange les questions
                GameManager.getInstance().shuffleQuestions();

            }
        });
    }
    public void randomQuestions(){
        //Affiche la question actuelle dans les libellés
        //Si la liste est entièrement parcourue, affiche les bouton "menu" et "restart"
        if (indexQuestion < questionList.size()){
            currentQuestion = questionList.get(indexQuestion).getQuestion();
            TV_player1Question.setText(currentQuestion);
            TV_player2Question.setText(currentQuestion);

            indexQuestion++;
        }else{
            TV_player1Question.setText(R.string.end_of_game);
            TV_player2Question.setText(R.string.end_of_game);
            BT_questionPlayer1.setEnabled(false);
            BT_questionPlayer2.setEnabled(false);
            RL_menuRestart.setVisibility(View.VISIBLE);

        }
    }
}
