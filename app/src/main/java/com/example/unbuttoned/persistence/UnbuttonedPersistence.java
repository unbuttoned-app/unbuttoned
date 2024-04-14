package com.example.unbuttoned.persistence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author julian
 * Simple & easy way to store highscore
 */
public class UnbuttonedPersistence {

    final private SharedPreferences prefs;
    final private SharedPreferences.Editor editor;
    final private static String HIGHSCORE = "HIGHSCORE";

    public UnbuttonedPersistence(Context ctx) {
        prefs = ctx.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public int getHighScore() {
        return prefs.getInt(HIGHSCORE, 0);
    }

    public void setHighScore(int hs) {
        editor.putInt(HIGHSCORE, hs);
        editor.apply();
    }

    /**
     * Update to new score if it is greater than the last one
     * @param score the achieved score
     */
    public void updateHighScore(int score) {
        if (score > getHighScore()) {
            setHighScore(score);
        }
    }
}
