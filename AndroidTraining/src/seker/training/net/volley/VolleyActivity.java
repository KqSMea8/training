/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.net.volley;


import seker.common.BaseActivity;
import seker.common.BaseApplication;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.android.common.logging.Log;

/**
 * 
 * @author seker
 * @since 2013年11月22日
 */
public class VolleyActivity extends BaseActivity {
    public static final String TAG = "net_simple";
    
    private RequestQueue mRequestQueue;
    
    private ImageLoader mImageLoader;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        
        mImageLoader = new ImageLoader(mRequestQueue, new ImageCache() {
            @Override
            public Bitmap getBitmap(String arg0) {
                return null;
            }

            @Override
            public void putBitmap(String arg0, Bitmap arg1) {
            }
        });
        
        String url = "http://www.baidu.com";
        StringRequest request = new StringRequest(Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        });
        mRequestQueue.add(request);
    }
    
    @Override
    protected void onStop() {
        mRequestQueue.stop();
        super.onStop();
    }
}
