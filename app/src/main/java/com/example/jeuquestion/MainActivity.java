package com.example.jeuquestion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton BT_newPlayer;
    private MaterialButton BT_newGame;
    private EditText ET_addPlayer1;
    private EditText ET_addPlayer2;
    private ImageView IV_menu;
    private String player1;
    private String player2;
    private int newDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BT_newPlayer = findViewById(R.id.btn_add_player);
        BT_newGame = findViewById(R.id.btn_new_game);
        ET_addPlayer1 = findViewById(R.id.edit_add_player_1);
        ET_addPlayer2 = findViewById(R.id.edit_add_player_2);
        IV_menu = findViewById(R.id.menu_icon);
        Intent mainActivity = getIntent();
        newDelay = mainActivity.getIntExtra("newDelay", 5000);
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

                if (!(ET_addPlayer1.getText().toString().isEmpty()
                        || ET_addPlayer2.getText().toString().isEmpty())) {
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

                if (!(ET_addPlayer1.getText().toString().isEmpty()
                        || ET_addPlayer2.getText().toString().isEmpty())) {
                    BT_newGame.setVisibility(View.VISIBLE);

                } else {
                    BT_newGame.setVisibility(View.INVISIBLE);
                }
            }
        });
        IV_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsActivity);
            }
        });

        BT_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Créer une instance contenant le nom des joueurs
                Intent gameActivity = new Intent(MainActivity.this, gameActivity.class);
                gameActivity.putExtra("player1Name", player1);
                gameActivity.putExtra("player2Name", player2);
                gameActivity.putExtra("newDelay", newDelay);
                startActivity(gameActivity);
            }
        });
    }

}