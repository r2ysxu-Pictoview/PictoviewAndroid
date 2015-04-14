package com.viewer.model;

import android.graphics.Bitmap;

/**
 * Created by ArthurXu on 13/04/2015.
 */
public class AlbumInfo {
    private long id;
    private String name;
    private String subtitle;
    private Bitmap bitmap;

    public AlbumInfo(long id, String name, String subtitle) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
