package com.viewer.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by ArthurXu on 13/04/2015.
 */
public class AlbumInfo implements Serializable, Comparable<AlbumInfo> {
    private final long id;
    private String name;
    private String subtitle;
    private long coverId;
    private Bitmap bitmap;

    public AlbumInfo(long id, String name, String subtitle, long coverId) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.coverId = coverId;
    }

    public long getCoverId() {
        return coverId;
    }

    public void setCoverId(long coverId) {
        this.coverId = coverId;
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

    @Override
    public int compareTo(AlbumInfo s) {
        if (this.name == null) return 0;
        return this.name.compareTo(s.getName());
    }
}
