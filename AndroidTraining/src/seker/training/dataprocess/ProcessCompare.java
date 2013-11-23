/*
 * Copyright (C) 2013 Seker Inc. All rights reserved.
 */
package seker.training.dataprocess;

import java.io.IOException;
import java.io.InputStream;

import seker.common.utils.StreamUtils;
import seker.training.dataprocess.json.android.AndroidJsonParser;
import seker.training.dataprocess.json.fast.FastJsonParser;
import seker.training.dataprocess.json.gson.GsonParser;
import seker.training.dataprocess.protobuf.ProtoParser;
import seker.training.dataprocess.xml.dom.DomXmlParser;
import seker.training.dataprocess.xml.pull.PullXmlParser;
import seker.training.dataprocess.xml.sax.SaxXmlParser;
import android.content.Context;
import android.util.Log;

/**
 * 
 * @author liuxinjian
 * @since 2013年11月23日
 */
public class ProcessCompare implements Runnable {
    
    private Context mContext;
    
    public ProcessCompare(Context context) {
        mContext = context.getApplicationContext();
    }
    
    @Override
    public void run() {
        
        final int N = 100;
        
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open("data/data.xml");
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
                InputStream is = mContext.getAssets().open("data/data.xml");
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
                InputStream is = mContext.getAssets().open("data/data.xml");
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
                InputStream is = mContext.getAssets().open("data/data.json");
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
                InputStream is = mContext.getAssets().open("data/data.json");
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
                InputStream is = mContext.getAssets().open("data/data.json");
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
                InputStream is = mContext.getAssets().open("data/data.pb");
                new ProtoParser().parse(is);
                StreamUtils.closeSafely(is);
            }
            long end = System.currentTimeMillis();
            Log.e("test", "Proto Buffer: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
