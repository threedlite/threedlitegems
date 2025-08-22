package com.threedlite.app;

import android.graphics.Canvas;

public abstract class AbstractThreeDWallpaper {
    
    protected boolean initialized = false;
    
    public abstract void reinit();
    public abstract void draw(Canvas canvas);
    
    protected double rnd(double r) {
        return Math.random() * r;
    }
}