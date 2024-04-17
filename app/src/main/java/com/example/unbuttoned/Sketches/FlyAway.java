package com.example.unbuttoned.Sketches;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;

public class FlyAway extends ButtonSketch {


    final private OnBackPressedCallback backPress;
    public FlyAway(String name, Button button, TextView textView, OnBackPressedDispatcher backButtonDispatcher) {
        super(name, button, textView);


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
        textView.setText("Try and catch me if you can!\n You can't get me BACK!");
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
