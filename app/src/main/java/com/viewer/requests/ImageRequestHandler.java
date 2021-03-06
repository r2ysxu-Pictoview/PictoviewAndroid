package com.viewer.requests;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.viewer.view.adapters.ImagePagerAdapter;

/**
 * Created by ArthurXu on 16/04/2015.
 */
public class ImageRequestHandler extends ClientRequestHandler {

    private final long userid = 1;
    private ImagePagerAdapter adapter;
    private int index;

    private static final String urlPath = "/PictureViewerRestServer/rest/viewer/image";

    public ImageRequestHandler(Context context, ImagePagerAdapter adapter) {
        super(context);
        this.adapter = adapter;
    }

    public void loadImage(int index) {
        long photoId = adapter.getPhotoInfos().get(index).getId();
        sendImageRequest(urlPath + "?usr=" + userid + "&photoid=" + photoId);
        this.index = index;
    }

    private void sendImageRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, domainName + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onImageResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onClientErrorResponse(error);
            }
        }
        );
        queue.add(stringRequest);
    }

    private void onImageResponse(String response) {
        Bitmap bitmap = decodeImage(response);
        adapter.getFragments().get(index).loadImageBitmap(bitmap);
        adapter.notifyDataSetChanged();
        adapter.prefetchNextFragment(index++);
    }

    private void onClientErrorResponse(VolleyError error) {
        error.printStackTrace();
    }
}
