package com.ktu.vavadoge;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;

public class RequestGate {
    private static RequestGate instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private RequestGate(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();


    }

    public static synchronized RequestGate getInstance(Context context) {
        if (instance == null) {
            instance = new RequestGate(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            CookieManager cookieManage = new CookieManager();
            CookieHandler.setDefault(cookieManage);
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());

        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
