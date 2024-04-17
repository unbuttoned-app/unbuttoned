package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unbuttoned.R;

/**
 * @author julian
 * Expect just a simple tab on the button
 */
public class BasicClick extends ButtonSketch {

    public BasicClick(String name, Button button, TextView textView) {
        super(name, button, textView);
    }

    @Override
    public void startSketch() {
        super.startSketch();
        textView.setText(R.string.don_t_press);
    }

    @Override
    public void onClick(View v) {
        float newTextSize = (float) (textView.getTextSize()*1.15);
        if(newTextSize > 200){
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize);
        } else {
            textView.setTextSize(newTextSize);
        }
        Log.d("BasicClick","clicked, completed challenge, Text increased to: " + newTextSize);
        endSketch(true);
    }
}
