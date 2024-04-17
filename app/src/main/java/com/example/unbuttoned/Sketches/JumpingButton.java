package com.example.unbuttoned.Sketches;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.example.unbuttoned.R;

/**
 * @author julian
 * Visual feedback for the user clicking the button
 */
public class JumpingButton extends ButtonSketch {

    int clickCount = 0;
    int springDiff = 0;

    public JumpingButton(String name, Button button, TextView textView) {
        super(name, button, textView);
    }
    @Override
    public void startSketch() {
        super.startSketch();
    }

    @Override
    public void onClick(View v) {
        switch (clickCount) {
            case 0:
                spring(200);
                break;
            case 1:
                spring(400);
                rotate(50);
                textView.setText(R.string.you_didn_t_expect_that);
                break;
            case 2:
                spring(-600);

                rotate(-80);
                break;
            default:
                spring(-springDiff);
                rotate(0);
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
    private void rotate(int rotation){
        final SpringAnimation spring = new SpringAnimation(
                button, DynamicAnimation.ROTATION_X, rotation);
        spring.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        spring.start();
    }
}
