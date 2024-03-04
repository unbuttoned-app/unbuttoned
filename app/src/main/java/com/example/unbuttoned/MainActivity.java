package com.example.unbuttoned;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


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
