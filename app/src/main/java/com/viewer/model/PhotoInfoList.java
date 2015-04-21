package com.viewer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ArthurXu on 16/04/2015.
 */
public class PhotoInfoList implements Serializable {

    private List<PhotoInfo> photoInfos;

    public PhotoInfoList(List<PhotoInfo> photoInfos) {
        this.photoInfos = photoInfos;
    }

    public List<PhotoInfo> getPhotoInfos() {
        return photoInfos;
    }

    public void setPhotoInfos(List<PhotoInfo> photoInfos) {
        this.photoInfos = photoInfos;
    }
}
