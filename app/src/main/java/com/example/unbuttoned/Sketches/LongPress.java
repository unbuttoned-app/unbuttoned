package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unbuttoned.R;

/**
 * @author julian
 * expects a longpress by the user
 * fails on short press
 */
public class LongPress extends ButtonSketch {
    public LongPress(String name, Button button, TextView textView) {
        super(name, button, textView);
    }
    @Override
    public void startSketch() {
        super.startSketch();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize);
        textView.setText(R.string.i_bet_you_cant_hold_on_for_long_enough);
        button.setOnLongClickListener(v -> {
            Log.d("LongPress", "You did a longpress, great");
            endSketch(true);
            button.setOnLongClickListener(null);
            return true;
        });
    }

    @Override
    public void onClick(View v) {
        Log.d("LongPress", "You did not longpress, challenge failed");
        endSketch(false);
    }
}
