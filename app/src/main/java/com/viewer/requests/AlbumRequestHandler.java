package com.viewer.requests;

import android.content.Context;
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

/**
 * Created by ArthurXu on 12/04/2015.
 */
public class AlbumRequestHandler extends ClientRequestHandler {

    private Context context;
    private AlbumExpandableListAdapter albumAdapter;

    public AlbumRequestHandler(Context context) {
        super(context);
    }

    public void loadAlbumList(AlbumExpandableListAdapter albumAdapter) {
        sendListResponse("/PictureViewerRestServer/rest/viewer/albums");
        this.albumAdapter = albumAdapter;
    }

    private void sendListResponse(String url) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, domainName+url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onClientResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onClientErrorResponse();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void onClientResponse(String response) {
        try {
            JSONArray albumList = new JSONArray(response);

            for (int i = 0; i < albumList.length(); i++) {
                JSONObject obj = albumList.getJSONObject(i);

                long id = obj.getLong("id");
                String name = obj.getString("name");
                albumAdapter.getAlbumInfos().add(new AlbumInfo(id, name, "subtitle"));
            }
            albumAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onClientErrorResponse() {
        Log.i("requests", "Unsuccessful retrieval");
    }
}
