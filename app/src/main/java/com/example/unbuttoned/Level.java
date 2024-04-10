package com.example.unbuttoned;

import com.example.unbuttoned.Sketches.ButtonSketch;

/**
 * @author julian
 * Model class representing a level
 * as a combination of a challenge (sketch done by the button) and points for the user
 */
public class Level {
    private final String name;
    private final ButtonSketch sketch;
    private final int points;

    public Level(String name, ButtonSketch sketch, int points) {
        this.name = name;
        this.sketch = sketch;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public ButtonSketch getSketch() {
        return sketch;
    }

    public int getPoints() {
        return points;
    }
}
