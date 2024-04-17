package com.example.unbuttoned.Sketches;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author julian
 * Makes the text of the button empty
 */
public class EmptyText extends ButtonSketch {

    String originalText = "";
    int count;
    public EmptyText(String name, Button button, TextView textView) {
        super(name, button, textView);
    }
    @Override
    public void startSketch() {
        super.startSketch();
        originalText = button.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (count) {
            case 0: // fall through
            case 1: // fall through
            case 2: // redo
                button.setText("");
                break;
            default:
                button.setText(originalText);
                endSketch(true);
                break;
        }
        count++;
    }

}
