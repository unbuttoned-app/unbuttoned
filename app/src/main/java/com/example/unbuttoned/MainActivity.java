package com.example.unbuttoned;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    int score = 0;
    TextView scoreTV;
    String scoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button press = findViewById(R.id.main_btn);
        scoreTV = findViewById(R.id.score_tv);
        scoreLabel = getResources().getString(R.string.score_txt);

        setScore(0);
        press.setOnClickListener(v -> incrementScore());

        final FloatingActionButton infoButton = findViewById(R.id.info_btn);
        infoButton.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));

    }

    private void setScore(int score) {
        this.score = score;
        final String txt = scoreLabel.concat(": " + score);
        scoreTV.setText(txt);
    }

    private void incrementScore() {
        setScore(score + 1);
    }
}
