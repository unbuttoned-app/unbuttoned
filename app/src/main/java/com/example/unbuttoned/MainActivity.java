package com.example.unbuttoned;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unbuttoned.Sketches.BasicClick;
import com.example.unbuttoned.Sketches.EmptyText;
import com.example.unbuttoned.Sketches.JumpingButton;
import com.example.unbuttoned.Sketches.LongPress;
import com.example.unbuttoned.Sketches.TextAlignment;
import com.example.unbuttoned.Sketches.TextResort;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int score = 0;
    TextView scoreTV;
    String scoreLabel;
    private List<Level> levels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button press = findViewById(R.id.main_btn);
        scoreTV = findViewById(R.id.score_tv);
        scoreLabel = getResources().getString(R.string.score_txt);

        // Representation of the levels
        levels = Arrays.asList(
                new Level("Basic click", new BasicClick("BasicClick", press), 1),
                new Level("Long press", new LongPress("LongPress", press), 5),
                new Level("Text align", new TextAlignment("Alignment", press), 2),
                new Level("Resorting", new TextResort("Resorting", press), 2),
                new Level("Empty text", new EmptyText("Empty", press), 2),
                new Level("Jump button", new JumpingButton("Jump", press), 3)
        );

        setScore(0);

        final FloatingActionButton infoButton = findViewById(R.id.info_btn);
        infoButton.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));

        runLevel(0);
    }

    private void setScore(int score) {
        Log.d("main", "score before " + this.score + " delta " + score);
        this.score += score;
        final String txt = scoreLabel.concat(": " + this.score);
        scoreTV.setText(txt);
    }

    private void runLevel(int iLvl) {
        if (iLvl > levels.size()-1) {
            Log.d("main", "game finished");
            Toast
                    .makeText(this, "Congratulations, well done", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        final Level level = levels.get(iLvl);
        final int pts = level.getPoints();
        Log.d("main", "Running level " + level.getName());
        level.getSketch().setOnSketchEnded((successful -> {
            Log.d("main","Level "
                    + level.getName() + " completed successfuly?: " + successful);
                if (!successful) {
                    setScore(-pts);
                    runLevel(iLvl); // redo level
                    Toast
                        .makeText(this, "You missed it, try again", Toast.LENGTH_SHORT)
                        .show();
                } else {
                    setScore(pts);
                    runLevel(iLvl+1); // next level
                }
        }));
        level.getSketch().startSketch();
    }
}
