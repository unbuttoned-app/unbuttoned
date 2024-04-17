package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unbuttoned.R;

/**
 * @author julian
 * Re-sorts the string displayed on the button in ugly ways
 */
public class TextResort extends ButtonSketch {

    private int count = 0;
    private String origText;
    private String resorted = "";

    public TextResort(String name, Button button, TextView textView) {
        super(name, button, textView);
    }

    @Override
    public void startSketch() {
        super.startSketch();
        origText = button.getText().toString();
        textView.setText(R.string.if_there_is_no_press_you_wouldn_t_press_it_right);
    }

    @Override
    public void endSketch(boolean successful){
        super.endSketch(successful);
        textView.setText("WHYYYY\nIt hurts");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize*2);
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
