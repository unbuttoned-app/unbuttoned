package com.example.unbuttoned.Sketches;

import android.view.View;
import android.widget.Button;


import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;

public class FlyAway extends ButtonSketch {


    final private OnBackPressedCallback backPress;
    public FlyAway(String name, Button button, OnBackPressedDispatcher backButtonDispatcher) {
        super(name, button);


        backPress = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getTheButtonAgain();
                endSketch(true);
            }
        };

        backButtonDispatcher.addCallback(backPress);
    }


    @Override
    protected void endSketch(boolean successful) {
        super.endSketch(successful);
        backPress.setEnabled(false); // disabled
    }

    @Override
    public void onClick(View v) {
        flyButtonAway();
    }

    private void flyButtonAway() {
        button.animate()
                .translationX(button.getRight())
                .rotation(360)
                .setDuration(200);
    }

    private void getTheButtonAgain() {
        button.animate()
                .translationX(0)
                .rotation(0)
                .setDuration(200);
    }
}
