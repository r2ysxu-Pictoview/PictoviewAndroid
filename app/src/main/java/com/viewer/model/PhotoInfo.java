package com.viewer.model;

import java.io.Serializable;

/**
 * Created by ArthurXu on 15/04/2015.
 */
public class PhotoInfo implements Serializable, Comparable<PhotoInfo> {

    private final long id;
    private String name;

    public PhotoInfo(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @Override
    public int compareTo(PhotoInfo s) {
        if (s == null) return -1;
        if (this.name == null) return -1;
        return this.name.compareTo(s.getName());
    }
}
