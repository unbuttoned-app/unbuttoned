package com.example.unbuttoned.Sketches;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author julian
 * This abstract class represents a "sketch", it provides a challenge to the user
 * that must be completed and can fail
 * callbacks are used for that
 */
abstract public class ButtonSketch implements View.OnClickListener {
    final protected String name;
    final protected Button button;
    private boolean active = false;
    private IOnCompletion onCompletion;

    public ButtonSketch(String name, Button button) {
        this.name = name;
        this.button = button;
    }

    public void startSketch() {
        Log.d("ButtonSketch", "Register click callback");
        button.setOnClickListener(this);
        active = true;
    }
    protected void endSketch(boolean successful) {
        button.setOnClickListener(null);
        active = false;
        onCompletion.onCompletion(successful);
    }

    public abstract void onClick(View v);

    public void setOnSketchEnded(final IOnCompletion cb) {
        onCompletion = cb;
    }

    protected boolean isActive() {
        return active;
    }
}
