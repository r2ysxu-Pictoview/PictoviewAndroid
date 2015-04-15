package com.viewer.requests;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.viewer.model.AlbumInfo;
import com.viewer.view.adapters.AlbumExpandableListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ArthurXu on 12/04/2015.
 */
public class AlbumRequestHandler extends ClientRequestHandler {

    private Context context;
    private AlbumExpandableListAdapter albumAdapter;
    private HashMap<Long, AlbumInfo> albumMap;
    private final long userid = 1;

    public AlbumRequestHandler(Context context) {
        super(context);
    }

    public void loadAlbumList(AlbumExpandableListAdapter albumAdapter) {
        sendListReqest("/PictureViewerRestServer/rest/viewer/albums");
        this.albumAdapter = albumAdapter;
    }

    private void sendListReqest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, domainName+url,
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
        });
        queue.add(stringRequest);
    }

    private void sendCoverImageRequest(final long albumid, String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, domainName+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onCoverImageResponse(albumid, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onClientErrorResponse();
            }
        });
        queue.add(stringRequest);
    }

    private void onListResponse(String response) {
        try {
            List<AlbumInfo> albumInfos =  albumAdapter.getAlbumInfos();
            JSONArray albumList = new JSONArray(response);
            albumMap = new HashMap<Long, AlbumInfo>();

            for (int i = 0; i < albumList.length(); i++) {
                JSONObject obj = albumList.getJSONObject(i);

                long id = obj.getLong("id");
                String name = obj.getString("name");
                long coverId = obj.getLong("coverId");
                AlbumInfo albumInfo = new AlbumInfo(id, name, "subtitle", coverId);
                if (coverId != 0) {
                    albumMap.put(id, albumInfo);
                    sendCoverImageRequest(id, "/PictureViewerRestServer/rest/viewer/thumbnail?usr=" + userid + "&photoid=" + coverId);
                } else {
                    albumInfos.add(albumInfo);
                    albumAdapter.notifyDataSetChanged();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onCoverImageResponse(long albumid, String response) {
        List<AlbumInfo> albumInfos =  albumAdapter.getAlbumInfos();
        AlbumInfo albumInfo = albumMap.get(albumid);

        // Decode Image
        byte[] imageBytes = Base64.decode(response, Base64.URL_SAFE);
        Log.i("bytes", imageBytes.toString());
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        albumInfo.setBitmap(imageBitmap);

        albumInfos.add(albumInfo);

        albumAdapter.notifyDataSetChanged();
    }

    private void onClientErrorResponse() {
        Log.i("requests", "Unsuccessful retrieval");
    }
}
