package com.example.unbuttoned.Sketches;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.unbuttoned.R;

/**
 * @author julian
 * creates a popup on press, expects right random choice A/B
 */
public class PopupPresser extends ButtonSketch {
    private final Context ctx;
    private PopupMenu popupMenu;

    public PopupPresser(String name, Button button, TextView textView, Context ctx) {
        super(name, button, textView);
        this.ctx = ctx;
    }


    @Override
    public void startSketch() {
        super.startSketch();
        textView.setText(R.string.choose_wisely);
        popupMenu = new PopupMenu(ctx, button);
        popupMenu.getMenuInflater().inflate(R.menu.button_popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            final int clickedId = item.getItemId();
            if (clickedId == R.id.itemA) {
                endSketch(isA());
            } else if (clickedId == R.id.itemB) {
                endSketch(!isA());
            }
            return false;
        });
    }

    @Override
    public void onClick(View v) {
        popupMenu.show();
    }


    private boolean isA() {
        return (Math.random() * 2) > 1;
    }
}
