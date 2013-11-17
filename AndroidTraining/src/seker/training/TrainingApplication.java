/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training;

import java.io.IOException;
import java.io.InputStream;

import seker.common.BaseApplication;
import seker.common.utils.StreamUtils;
import seker.training.dataprocess.json.android.AndroidJsonParser;
import seker.training.dataprocess.json.fast.FastJsonParser;
import seker.training.dataprocess.json.gson.GsonParser;
import seker.training.dataprocess.protobuf.ProtoParser;
import seker.training.dataprocess.xml.dom.DomXmlParser;
import seker.training.dataprocess.xml.pull.PullXmlParser;
import seker.training.dataprocess.xml.sax.SaxXmlParser;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class TrainingApplication extends BaseApplication {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        final int N = 100;
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.xml");
                new DomXmlParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Dom xml: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.xml");
                new PullXmlParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Pull xml: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.xml");
                new SaxXmlParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "SAX xml: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.json");
                new AndroidJsonParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Android Json: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.json");
                new GsonParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Gson: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.json");
                new FastJsonParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Fast Json: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = getAssets().open("data/data.pb");
                new ProtoParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Proto Buffer: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
    
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
