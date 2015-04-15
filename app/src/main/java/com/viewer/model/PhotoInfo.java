package com.viewer.model;

import android.graphics.Bitmap;

/**
 * Created by ArthurXu on 15/04/2015.
 */
public class PhotoInfo {

    private final long id;
    private Bitmap bitmap;

    public PhotoInfo(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
