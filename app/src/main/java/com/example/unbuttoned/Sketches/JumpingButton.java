package com.example.unbuttoned.Sketches;

import android.view.View;
import android.widget.Button;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

/**
 * @author julian
 * Visual feedback for the user clicking the button
 */
public class JumpingButton extends ButtonSketch {

    int clickCount = 0;
    int springDiff = 0;

    public JumpingButton(String name, Button button) {
        super(name, button);
    }

    @Override
    public void startSketch() {
        super.startSketch();
    }

    @Override
    public void onClick(View v) {
        switch (clickCount) {
            case 0:
                spring(100);
                break;
            case 1:
                spring(200);
                break;
            case 2:
                spring(-250);
                break;
            default:
                spring(-springDiff);
                endSketch(true);
                break;
        }
        clickCount++;
    }

    private void spring(int delta) {
        springDiff += delta;
        final SpringAnimation spring = new SpringAnimation(
                button, DynamicAnimation.TRANSLATION_Y, delta);
        spring.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        spring.start();
    }
}
