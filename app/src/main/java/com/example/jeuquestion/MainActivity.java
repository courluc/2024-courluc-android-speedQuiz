package com.example.jeuquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton BT_newPlayer;
    private MaterialButton BT_newGame;
    private EditText ET_addPlayer1;
    private EditText ET_addPlayer2;
    private String player1;
    private String player2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BT_newPlayer = findViewById(R.id.btn_add_player);
        BT_newGame = findViewById(R.id.btn_new_game);
        ET_addPlayer1 = findViewById(R.id.edit_add_player_1);
        ET_addPlayer2 = findViewById(R.id.edit_add_player_2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BT_newPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Affiche le champs de saisie pour ajouter le nom du joueur
                ET_addPlayer1.setVisibility(View.VISIBLE);
            }
        });

        ET_addPlayer1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ET_addPlayer2.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Affiche le bouton "new game" uniquement si du texte est présent dans les deux champs
                player1 = ET_addPlayer1.getText().toString();

                if (!(ET_addPlayer1.getText().toString().equals("")
                        || ET_addPlayer2.getText().toString().equals(""))) {
                    BT_newGame.setVisibility(View.VISIBLE);

                } else {
                    BT_newGame.setVisibility(View.INVISIBLE);
                }
            }
        });
        ET_addPlayer2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Affiche le bouton "new game" uniquement si du texte est présent dans les deux champs
                player2 = ET_addPlayer2.getText().toString();

                if (!(ET_addPlayer1.getText().toString().equals("")
                        || ET_addPlayer2.getText().toString().equals(""))) {
                    BT_newGame.setVisibility(View.VISIBLE);

                } else {
                    BT_newGame.setVisibility(View.INVISIBLE);
                }
            }
        });

        BT_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Créer une instance contenant le nom des joueurs
                Intent gameActivity = new Intent(MainActivity.this, gameActivity.class);
                gameActivity.putExtra("player1Name", player1);
                gameActivity.putExtra("player2Name", player2);
                startActivity(gameActivity);
            }
        });
    }

}