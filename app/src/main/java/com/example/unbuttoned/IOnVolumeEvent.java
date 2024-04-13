package com.example.unbuttoned;

import android.view.KeyEvent;

@FunctionalInterface
public interface IOnVolumeEvent {
    void onKeyPress(KeyEvent ev);
}
