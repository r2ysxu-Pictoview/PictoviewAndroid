package com.viewer.requests;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.viewer.model.PhotoInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ArthurXu on 15/04/2015.
 */
public class PhotoRequestHandler extends ClientRequestHandler {

    private GridLayout photoGrid;
    private HashMap<Long, PhotoInfo> photoMap;
    private final long userid = 1;

    public PhotoRequestHandler(Context context) {
        super(context);
    }

    public void loadPhotoList(GridLayout photoGrid, long albumId) {
        this.photoGrid = photoGrid;
        photoGrid.removeAllViews();
        sendListReqest("/PictureViewerRestServer/rest/viewer/photos?usr=" + userid + "&albumid=" + albumId);
    }

    private void sendListReqest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, domainName + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onListResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onClientErrorResponse();
            }
        }
        );
        queue.add(stringRequest);
    }

    private void onListResponse(String response) {
        try {
            JSONArray photoList = new JSONArray(response);
            photoMap = new HashMap<Long, PhotoInfo>();

            for (int i = 0; i < photoList.length(); i++) {
                JSONObject obj = photoList.getJSONObject(i);

                long id = obj.getLong("id");
                PhotoInfo photoInfo = new PhotoInfo(id);

                if (id != 0) {
                    photoMap.put(id, photoInfo);
                    sendImageThumbnailRequest(id, "/PictureViewerRestServer/rest/viewer/thumbnail?usr=" + userid + "&photoid=" + id);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendImageThumbnailRequest(final long id, String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, domainName+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onImageResponse(id, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onClientErrorResponse();
            }
        });
        queue.add(stringRequest);
    }

    private void onImageResponse(long id, String response) {
        PhotoInfo photoInfo = photoMap.get(id);
        photoInfo.setBitmap(decodeImage(response));

        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(photoInfo.getBitmap());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));

        photoGrid.addView(imageView);
    }

    private void onClientErrorResponse() {
        Log.i("requests", "Unsuccessful retrieval");
    }
}
