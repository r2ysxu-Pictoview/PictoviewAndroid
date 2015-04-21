package com.viewer.view.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.viewer.model.PhotoInfo;
import com.viewer.requests.ImageRequestHandler;
import com.viewer.view.ScreenSlidePageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArthurXu on 16/04/2015.
 */
public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private List<PhotoInfo> photoInfos;
    private List<ScreenSlidePageFragment> fragments;
    private List<Boolean> imageLoaded;
    private Context context;

    public ImagePagerAdapter(Context context, FragmentManager fm, List<PhotoInfo> photoInfos) {
        super(fm);
        this.photoInfos = photoInfos;
        this.context = context;
        imageLoaded = new ArrayList<Boolean>();
        generateFragments();
    }

    private void generateFragments() {
        fragments = new ArrayList<ScreenSlidePageFragment>();
        for (int i = 0; i < photoInfos.size(); i++) {
            fragments.add(new ScreenSlidePageFragment());
            imageLoaded.add(false);
        }
    }

    public List<PhotoInfo> getPhotoInfos() {
        return photoInfos;
    }

    public boolean isImageLoaded(int i) {
        return imageLoaded.get(i);
    }

    @Override
    public Fragment getItem(int i) {
        ImageRequestHandler handler = new ImageRequestHandler(context, this);
        if (!isImageLoaded(i)) {
            handler.loadImage(i, fragments.get(i));
            imageLoaded.set(i, true);
        }
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return photoInfos.size();
    }
}
