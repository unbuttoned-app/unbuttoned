package com.example.unbuttoned.Sketches;

import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unbuttoned.IOnVolumeEvent;
import com.example.unbuttoned.R;
import com.example.unbuttoned.VolumeObserver;

/**
 * @author julian
 * Listen for volume down key press
 */
public class VolumeKeyPresser extends ButtonSketch {

    final private VolumeObserver volumeObserver;
    private IOnVolumeEvent volumeEvent;
    public VolumeKeyPresser(String name, Button button, TextView textView, VolumeObserver volumeObserver) {
        super(name, button, textView);

        this.volumeObserver = volumeObserver;

    }

    @Override
    public void onClick(View v) {
        button.setEnabled(false); // gray
        textView.setText(R.string.your_music_down_here_is_way_too_loud);
    }

    @Override
    public void startSketch() {
        super.startSketch();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize);
        this.volumeEvent = (KeyEvent ev) -> {
            final int code = ev.getKeyCode();
            if (code == KeyEvent.KEYCODE_VOLUME_DOWN) {
                endSketch(true);
            }
        };

        volumeObserver.addOnVolumeEventCb(volumeEvent);
    }

    @Override
    protected void endSketch(boolean successful) {
        super.endSketch(successful);
        volumeObserver.removeOnVolumeEventCb(volumeEvent);
        button.setEnabled(true);
    }
}
