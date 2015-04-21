package com.viewer.view.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.viewer.activity.ImageActivity;
import com.viewer.model.PhotoInfo;
import com.viewer.model.PhotoInfoList;

import java.util.List;

/**
 * Created by ArthurXu on 15/04/2015.
 */
public class ImageViewOnClickListener implements View.OnClickListener {

    private Context context;
    private List<PhotoInfo> photoInfo;
    private int photoIndex;

    public ImageViewOnClickListener(Context context, List<PhotoInfo> photoInfo, int photoIndex) {
        this.photoInfo = photoInfo;
        this.photoIndex = photoIndex;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        selectImage();
    }

    private void selectImage() {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(ExtraConstants.PHOTO_INFO, new PhotoInfoList(photoInfo));
        intent.putExtra(ExtraConstants.PHOTO_INDEX, photoIndex);
        context.startActivity(intent);
    }
}