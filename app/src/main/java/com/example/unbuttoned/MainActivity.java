package com.example.unbuttoned;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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
import android.util.Log;
import android.widget.Toast;


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


        final Button press = findViewById(R.id.main_btn);
        scoreTV = findViewById(R.id.score_tv);
        scoreLabel = getResources().getString(R.string.score_txt);

        volume = new VolumeObserver();

        // Representation of the levels
        levels = Arrays.asList(
                new Level("Basic click", new BasicClick("BasicClick", press), 1),
                new Level("Long press", new LongPress("LongPress", press), 5),
                new Level("Text align", new TextAlignment("Alignment", press), 2),
                new Level("Resorting", new TextResort("Resorting", press), 2),
                new Level("Empty text", new EmptyText("Empty", press), 2),
                new Level("Jump button", new JumpingButton("Jump", press), 3),
                new Level("Fly away", new FlyAway("FlyAway", press, getOnBackPressedDispatcher()), 10),
                new Level("Volume key", new VolumeKeyPresser("VolumeKey", press, volume), 20),
                new Level("Invisible", new InvisiblePresser("Invisible", press), 7),
                new Level("Drag button", new DraggablePresser("Drag", press), 10),
                new Level("Popup", new PopupPresser("Popup", press, getApplicationContext()), 20)
        );

        setScore(0);

        final FloatingActionButton infoButton = findViewById(R.id.info_btn);


        startInfo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o != null && o.getResultCode() == RESULT_OK) {
                    if (o.getData() != null && o.getData().getExtras() != null) {
                        final boolean reset = o.getData().getExtras().getBoolean(AppInfoActivity.RESET_HSCORE);
                        if (reset) {
                            Log.d("Main", "Resetting");
                            persistence.setHighScore(0);
                        }
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
