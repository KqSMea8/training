/*
 * Copyright (C) 2013 Seker Inc. All rights reserved.
 */
package seker.training.dataprocess;

import java.io.IOException;
import java.io.InputStream;

import seker.common.speed.TimeLogger;
import seker.common.utils.StreamUtils;
import seker.training.dataprocess.json.android.AndroidJsonParser;
import seker.training.dataprocess.json.fast.FastJsonParser;
import seker.training.dataprocess.json.gson.GsonParser;
import seker.training.dataprocess.protobuf.ProtoParser;
import seker.training.dataprocess.xml.dom.DomXmlParser;
import seker.training.dataprocess.xml.pull.PullXmlParser;
import seker.training.dataprocess.xml.sax.SaxXmlParser;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author liuxinjian
 * @since 2013年11月23日
 */
public class ProcessCompare implements Runnable {
    
    private static final String DATA_DATA_XML = "data/data.xml";

    private static final String DATA_DATA_JSON = "data/data.json";

    private static final String DATA_DATA_PB = "data/data.pb";

    public static final String TAG = "parser";
    
    private Context mContext;
    
    public ProcessCompare(Context context) {
        mContext = context.getApplicationContext();
    }
    
    @Override
    public void run() {
        final int N = 100;
        try {
            final String model = "dom_xml";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_XML);
                new DomXmlParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "Dom xml: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            final String model = "pull_xml";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_XML);
                new PullXmlParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "Pull xml: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            final String model = "sax_xml";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_XML);
                new SaxXmlParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "SAX xml: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            
            final String model = "android_json";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_JSON);
                new AndroidJsonParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "Android Json: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            final String model = "google_json";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_JSON);
                new GsonParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "Gson: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            final String model = "fast_json";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_JSON);
                new FastJsonParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "Fast Json: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            final String model = "proto_buffer";
            long start = System.currentTimeMillis();
            TimeLogger.record(TAG, model, String.valueOf(0));
            for (int i = 0; i < N; i ++) {
                InputStream is = mContext.getAssets().open(DATA_DATA_PB);
                new ProtoParser().parse(is);
                StreamUtils.closeSafely(is);
                TimeLogger.record(TAG, model, String.valueOf(i + 1));
            }
            long end = System.currentTimeMillis();
            Log.e(TAG, "Proto Buffer: cost " + (end - start) + "ms.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                TimeLogger.export(Environment.getExternalStorageDirectory().getPath(), "parse.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
