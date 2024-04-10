package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author julian
 * Expect just a simple tab on the button
 */
public class BasicClick extends ButtonSketch {

    public BasicClick(String name, Button button) {
        super(name, button);
    }

    @Override
    public void startSketch() {
        super.startSketch();
    }

    @Override
    public void onClick(View v) {
        Log.d("BasicClick","clicked, completed challenge");
        endSketch(true);
    }
}
