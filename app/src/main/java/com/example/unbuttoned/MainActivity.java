package com.example.unbuttoned;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unbuttoned.Sketches.BasicClick;
import com.example.unbuttoned.Sketches.DraggablePresser;
import com.example.unbuttoned.Sketches.EmptyText;
import com.example.unbuttoned.Sketches.FlyAway;
import com.example.unbuttoned.Sketches.InvisiblePresser;
import com.example.unbuttoned.Sketches.JumpingButton;
import com.example.unbuttoned.Sketches.LongPress;
import com.example.unbuttoned.Sketches.PopupPresser;
import com.example.unbuttoned.Sketches.TextAlignment;
import com.example.unbuttoned.Sketches.TextResort;
import com.example.unbuttoned.Sketches.VolumeKeyPresser;
import com.example.unbuttoned.persistence.UnbuttonedPersistence;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private int score = 0;
    private TextView scoreTV;
    private String scoreLabel;
    private List<Level> levels;
    private VolumeObserver volume;
    private UnbuttonedPersistence persistence;

    ActivityResultLauncher<Intent> startInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persistence = new UnbuttonedPersistence(getApplicationContext());

        final TextView text = findViewById(R.id.showText);
        final Button press = findViewById(R.id.main_btn);
        scoreTV = findViewById(R.id.score_tv);
        scoreLabel = getResources().getString(R.string.score_txt);

        text.setTextColor(0xFFFF3300);

        volume = new VolumeObserver();

        // Representation of the levels
        levels = Arrays.asList(

                new Level("Basic click", new BasicClick("BasicClick", press, text), 1),
                new Level("Basic click", new BasicClick("BasicClick", press, text), 1),
                new Level("Basic click", new BasicClick("BasicClick", press, text), 1),
                new Level("Long press", new LongPress("LongPress", press, text), 5),
                new Level("Text align", new TextAlignment("Alignment", press, text), 2),
                new Level("Invisible", new InvisiblePresser("Invisible", press, text), 7),
                new Level("Resorting", new TextResort("Resorting", press, text), 2),
                new Level("Empty text", new EmptyText("Empty", press, text), 2),
                new Level("Jump button", new JumpingButton("Jump", press, text), 3),
                new Level("Fly away", new FlyAway("FlyAway", press, text, getOnBackPressedDispatcher()), 10),
                new Level("Volume key", new VolumeKeyPresser("VolumeKey", press, text, volume), 20),
                new Level("Invisible", new InvisiblePresser("Invisible", press, text), 7),
                new Level("Drag button", new DraggablePresser("Drag", press, text), 10),
                new Level("Popup", new PopupPresser("Popup", press, text, getApplicationContext()), 20),
                new Level("Invisible", new InvisiblePresser("Invisible", press, text), 7)
        );

        setScore(0);

        final FloatingActionButton infoButton = findViewById(R.id.info_btn);


        startInfo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
            if (o != null && o.getResultCode() == RESULT_OK) {
                if (o.getData() != null && o.getData().getExtras() != null) {
                    final boolean reset = o.getData().getExtras().getBoolean(AppInfoActivity.RESET_HSCORE);
                    if (reset) {
                        Log.d("Main", "Resetting");
                        persistence.setHighScore(0);
                    }
                }
            }
        });

        Intent infoIntent = new Intent(MainActivity.this, AppInfoActivity.class);

        infoButton.setOnClickListener(v ->{
            infoIntent.putExtra(AppInfoActivity.HSCORE_VALUE, persistence.getHighScore());
            startInfo.launch(infoIntent);
        });
        runLevel(0);
    }

    private void updateScore(int delta) {
        Log.d("main", "score before " + this.score + " delta " + delta);
        final int newScore = this.score + delta;
        setScore(newScore);
    }

    private void setScore(int score) {
        this.score = score;
        final String txt = scoreLabel.concat(": " + this.score);
        scoreTV.setText(txt);
    }

    private void runLevel(int iLvl) {
        if (iLvl > levels.size()-1) {
            Log.d("main", "game finished");
            Toast
                    .makeText(this, "Congratulations, well done", Toast.LENGTH_LONG)
                    .show();
            findViewById(R.id.main_btn).setEnabled(false);
            TextView textView = findViewById(R.id.showText);
            textView.setText(R.string.i_am_done);
            textView.setTextColor(0xFF00FF00);
            persistence.updateHighScore(this.score);
            return;
        }
        final Level level = levels.get(iLvl);
        final int pts = level.getPoints();
        Log.d("main", "Running level " + level.getName());
        level.getSketch().setOnSketchEnded((successful -> {
            Log.d("main","Level "
                    + level.getName() + " completed successfuly?: " + successful);
                if (!successful) {
                    updateScore(-pts);
                    runLevel(iLvl); // redo level
                    Toast
                        .makeText(this, "You missed it, try again", Toast.LENGTH_SHORT)
                        .show();
                } else {
                    updateScore(pts);
                    runLevel(iLvl+1); // next level
                }
        }));
        level.getSketch().startSketch();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        volume.notifyObservers(event);
        return super.onKeyDown(keyCode, event);
    }
}
