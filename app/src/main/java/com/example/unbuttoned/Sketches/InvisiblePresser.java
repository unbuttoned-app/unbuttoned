package com.example.unbuttoned.Sketches;

import android.view.View;
import android.widget.Button;

/**
 * @author julian
 * invisible button
 */
public class InvisiblePresser extends ButtonSketch {

    private int count;
    public InvisiblePresser(String name, Button button) {
        super(name, button);
    }

    @Override
    public void onClick(View v) {
        count++;
        if ((count %2) == 1) {
            button.setAlpha(0);
        } else {
            button.setAlpha(1);
            endSketch(true);
        }
    }
}
