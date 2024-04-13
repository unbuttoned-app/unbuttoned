package com.example.unbuttoned;


import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author julian
 * This should encapsulate the volume button a bit
 */
public class VolumeObserver {
    final private List<IOnVolumeEvent> observerCbs;

    public VolumeObserver() {
        observerCbs = new ArrayList<>();
    }

    public void notifyObservers(KeyEvent ev) {
        for (IOnVolumeEvent observer : observerCbs) {
            observer.onKeyPress(ev);
        }
    }

    public void addOnVolumeEventCb(IOnVolumeEvent listener) {
        observerCbs.add(listener);
    }

    public void removeOnVolumeEventCb(IOnVolumeEvent listener) {
        observerCbs.remove(listener);
    }
}
