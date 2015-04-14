package com.viewer.requests;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ArthurXu on 12/04/2015.
 */
public abstract class ClientRequestHandler {

    private Context context;
    protected RequestQueue queue;
    protected final String domainName = "http://192.168.1.100:8080";

    public ClientRequestHandler(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }
}
