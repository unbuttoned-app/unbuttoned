package com.example.unbuttoned.Sketches;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unbuttoned.R;

import java.util.Random;

/**
 * @author julian
 * invisible button
 */
public class InvisiblePresser extends ButtonSketch {

    private int count;
    public InvisiblePresser(String name, Button button, TextView textView) {
        super(name, button, textView);

    }

    @Override
    public void onClick(View v) {
        count++;
        if ((count %2) == 1) {
            button.setEnabled(false);
            button.setAlpha(0);
            int widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
            int heightPixels = Resources.getSystem().getDisplayMetrics().heightPixels;
            button.animate().translationX(new Random().nextInt(widthPixels)- (float) widthPixels /2);
            button.animate().translationY(new Random().nextInt(heightPixels) - (float) heightPixels /2);
            textView.setText(R.string.find_me_if_you_can);
            button.setEnabled(true);
        } else {
            button.setAlpha(1);
            button.animate().translationX(0);
            button.animate().translationY(0);
            endSketch(true);
        }
    }
}
