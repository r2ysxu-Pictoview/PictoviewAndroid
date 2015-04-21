package com.viewer.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewer.R;

/**
 * Created by ArthurXu on 16/04/2015.
 */
public class ScreenSlidePageFragment extends Fragment {

    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_image_slider, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        return rootView;
    }

    public void loadImageBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
