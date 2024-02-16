package com.example.jeuquestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private Button BT_addQuestion;
    private Button BT_changeDelay;
    private EditText ET_changeDelay;
    private EditText ET_addQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        BT_addQuestion = findViewById(R.id.bt_add_question);
        BT_changeDelay = findViewById(R.id.bt_change_delay);
        ET_addQuestion = findViewById(R.id.et_add_question);
        ET_changeDelay = findViewById(R.id.et_change_delay);

    }

    @Override
    protected void onStart() {
        super.onStart();
        BT_changeDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newDelay = Integer.parseInt(String.valueOf(ET_changeDelay.getText()));
                Intent gameActivity = new Intent(SettingsActivity.this, SettingsActivity.class);
                gameActivity.putExtra("newDelay", newDelay);
            }
        });
    }

}
