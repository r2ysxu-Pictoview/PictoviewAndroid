package com.viewer.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.viewer.R;
import com.viewer.model.PhotoInfo;
import com.viewer.model.PhotoInfoList;
import com.viewer.view.adapters.ImagePagerAdapter;
import com.viewer.view.controller.ExtraConstants;

import java.util.List;

public class ImageActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        fillImage();
    }

    private void fillImage() {
        ViewPager pager = (ViewPager) findViewById(R.id.image_pager);

        Bundle extras = getIntent().getExtras();
        List<PhotoInfo> photoInfos = ((PhotoInfoList) extras.get(ExtraConstants.PHOTO_INFO)).getPhotoInfos();
        int index = (Integer) extras.get(ExtraConstants.PHOTO_INDEX);


        ImagePagerAdapter adapter = new ImagePagerAdapter(this, getSupportFragmentManager(), photoInfos);

        pager.setAdapter(adapter);
        pager.setCurrentItem(index);
    }
}
