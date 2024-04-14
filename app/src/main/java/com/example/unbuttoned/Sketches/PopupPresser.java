package com.example.unbuttoned.Sketches;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.unbuttoned.R;

/**
 * @author julian
 * creates a popup on press, expects right random choice A/B
 */
public class PopupPresser extends ButtonSketch {
    private final Context ctx;
    private PopupMenu popupMenu;

    public PopupPresser(String name, Button button, Context ctx) {
        super(name, button);
        this.ctx = ctx;
    }

    @Override
    public void startSketch() {
        super.startSketch();
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
