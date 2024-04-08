package com.example.unbuttoned;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

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
        press.setOnClickListener(v -> {
            incrementScore();
            int b = 0;
            if ((score % 2) == 1) {
                b = 100;
            }
            final SpringAnimation spring = new SpringAnimation(press, DynamicAnimation.TRANSLATION_Y, b);
            spring.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
            spring.start();
        });

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
