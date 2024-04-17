package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author julian
 * changes alignment in multiple ways
 */
public class TextAlignment extends ButtonSketch{

    final List<Integer> alignments;
    int origAlignment;
    public TextAlignment(String name, Button button, TextView textView) {
        super(name, button, textView);
        alignments = new ArrayList<>(Arrays.asList(
                View.TEXT_ALIGNMENT_TEXT_START,
                View.TEXT_ALIGNMENT_TEXT_END
        ));
    }

    @Override
    public void startSketch() {
        super.startSketch();
        textView.setText("");
        origAlignment = button.getTextAlignment();
    }

    @Override
    public void onClick(View v) {
        if (alignments.isEmpty()) {
            endSketch(true);
            button.setTextAlignment(origAlignment);
            return;
        }

        final int alignment = alignments.remove(0);
        Log.d("TextAlignment", "using alignment " + alignment);
        button.setTextAlignment(alignment);
        button.setText(button.getText()); // update
    }
}
