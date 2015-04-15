package com.viewer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridLayout;

import com.viewer.R;
import com.viewer.requests.PhotoRequestHandler;
import com.viewer.view.controller.ExtraConstants;

public class PhotosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        fillPhotos();
    }

    private void fillPhotos() {
        PhotoRequestHandler handler = new PhotoRequestHandler(this);

        Long albumId = (Long) getIntent().getExtras().get(ExtraConstants.ALBUM_INFO);

        GridLayout photoGrid = (GridLayout) findViewById(R.id.grid_photos);
        handler.loadPhotoList(photoGrid, albumId);
    }

}
