package com.threedlite.gems;

import android.content.SharedPreferences;
import android.graphics.Canvas;

public class ThreeDLiteExoticsWallpaper extends BaseWallpaper {

    private ThreeDLiteClassicWallpaper classicRenderer;
    private ThreeDLiteGemsWallpaper gemsRenderer;
    private AbstractThreeDWallpaper currentRenderer;
    private String currentMode = "";

    public ThreeDLiteExoticsWallpaper() {
        classicRenderer = new ThreeDLiteClassicWallpaper();
        gemsRenderer = new ThreeDLiteGemsWallpaper(this);
    }

    @Override
    protected void reinit() {
        updateRenderer();
        if (currentRenderer != null) {
            currentRenderer.reinit();
        }
    }

    private void updateRenderer() {
        SharedPreferences prefs = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE);
        String mode = prefs.getString(SettingsActivity.DISPLAY_MODE_KEY, SettingsActivity.MODE_GEMS);
        
        if (!mode.equals(currentMode)) {
            currentMode = mode;
            if (SettingsActivity.MODE_CLASSIC.equals(mode)) {
                currentRenderer = classicRenderer;
            } else {
                currentRenderer = gemsRenderer;
            }
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        updateRenderer();
        if (currentRenderer != null) {
            currentRenderer.draw(canvas);
        }
    }
}