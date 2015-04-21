package com.viewer.requests;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ArthurXu on 12/04/2015.
 */
public abstract class ClientRequestHandler {

    protected Context context;
    protected RequestQueue queue;
    protected final String domainName = "http://192.168.1.100:8080";

    public ClientRequestHandler(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    protected Bitmap decodeImage(String response) {
        // Decode Image
        byte[] imageBytes = Base64.decode(response, Base64.URL_SAFE);
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return imageBitmap;
    }
}
