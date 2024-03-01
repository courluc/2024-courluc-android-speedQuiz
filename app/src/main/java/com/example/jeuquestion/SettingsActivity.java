package com.example.jeuquestion;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jeuquestion.Models.Question;
import com.example.jeuquestion.Models.SpeedGameSqLite;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private ImageView BT_close;
    private Button BT_changeDelay;
    private EditText ET_changeDelay;
    private EditText ET_addQuestion;
    private Button BT_addQuestion;
    private RadioButton RB_true;
    private RadioButton RB_false;
    public Intent settingsActivity;
    private boolean answer = false;
    String newQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        BT_close = findViewById(R.id.bt_settings_close);
        BT_changeDelay = findViewById(R.id.bt_change_delay);
        ET_addQuestion = findViewById(R.id.et_add_question);
        ET_changeDelay = findViewById(R.id.et_change_delay);
        BT_addQuestion = findViewById(R.id.bt_confirm_question);
        RB_true = findViewById(R.id.radio_button_1);
        RB_false = findViewById(R.id.radio_button_2);
        settingsActivity = new Intent(SettingsActivity.this, MainActivity.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        BT_changeDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newDelay = Integer.parseInt(String.valueOf(ET_changeDelay.getText())) * 1000;
                settingsActivity.putExtra("newDelay", newDelay);
                startActivity(settingsActivity);
            }
        });
        BT_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        BT_addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = RB_true.isChecked();
                addQuestion();
                finish();
            }
        });
    }

    /**
     * Ajoute la question saisie par l'utilisateur
     */
    public void addQuestion(){
        newQuestion = ET_addQuestion.getText().toString();
        SpeedGameSqLite helper = new SpeedGameSqLite(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        if (answer) {
            db.execSQL("INSERT INTO quiz VALUES (null, '" + newQuestion + "', 1)");
        }else
            db.execSQL("INSERT INTO quiz VALUES (null, '" + newQuestion + "', 0)");
        db.close();

    }
}

