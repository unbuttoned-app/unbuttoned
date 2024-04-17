package com.example.unbuttoned.Sketches;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author julian
 * Allows dragging the button
 */
public class DraggablePresser extends ButtonSketch{
    public DraggablePresser(String name, Button button, TextView textView) {
        super(name, button, textView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void startSketch() {
        super.startSketch();


        button.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData cd = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(cd, shadowBuilder, v, 0);
                    button.setOnTouchListener(null);
                    endSketch(true);

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
