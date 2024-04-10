package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author julian
 * expects a longpress by the user
 * fails on short press
 */
public class LongPress extends ButtonSketch {
    public LongPress(String name, Button button) {
        super(name, button);
    }

    @Override
    public void startSketch() {
        super.startSketch();
        button.setOnLongClickListener(v -> {
            Log.d("LongPress", "You did a longpress, great");
            endSketch(true);
            return true;
        });
    }

    @Override
    public void onClick(View v) {
        Log.d("LongPress", "You did not longpress, challenge failed");
        endSketch(false);
    }
}
