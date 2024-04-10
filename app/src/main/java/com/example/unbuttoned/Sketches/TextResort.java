package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author julian
 * Re-sorts the string displayed on the button in ugly ways
 */
public class TextResort extends ButtonSketch {

    private int count = 0;
    private String origText;
    private String resorted = "";

    public TextResort(String name, Button button) {
        super(name, button);
    }

    @Override
    public void startSketch() {
        super.startSketch();
        origText = button.getText().toString();
    }

    @Override
    public void onClick(View v) {
        Log.d("TextResort", "resorting");
        switch (count) {
            case 0:
                button.setText(reversed(origText));
                resorted = origText;
                break;
            case 1: // fall through
            case 2: // fall through
            case 3: // redo
                resorted = crazySort(resorted);
                button.setText(resorted);
                break;
            default:
                button.setText(origText);
                endSketch(true);
                break;
        }
        count++;
    }

    private String reversed(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private String crazySort(String str) {
        final StringBuilder stringBuilder = new StringBuilder();
        char[] characters = str.toCharArray();
        for (char c : characters) {
            c++;
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
