/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.threads;

import java.util.concurrent.TimeUnit;

import seker.common.BaseActivity;
import seker.common.threads.task.AsyncTask;
import seker.common.threads.task.AsyncTaskExecutor;
import android.os.Bundle;
import android.util.Log;

/**
 * @author xinjian.lxj
 *
 */
public class AsyncTaskTest extends BaseActivity {
    public static final String TAG = "AsyncTaskExecutor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AsyncTaskExecutor executor = AsyncTaskExecutor.getInstance();
        
        executor.executeSerially(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "executeSerially : Runnable : 1");
            }
        });
//        
//        executor.executeSerially(new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... params) {
//                Log.i(TAG, params[0]);
//                return null;
//            }
//        }, "executeSerially : AsyncTask : 1");
//        
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "execute : Runnable : 2");
//            }
//        });
//        
//        executor.execute(new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... params) {
//                Log.i(TAG, params[0]);
//                return null;
//            }
//        }, "execute : AsyncTask : 2");
//        
//        executor.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "scheduleAtFixedRate : Runnable : 3");
//            }
//        }, 3, 1, TimeUnit.SECONDS);
//        
//        executor.scheduleAtFixedRate(new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... params) {
//                Log.i(TAG, params[0]);
//                return null;
//            }
//        }, 3, 1, TimeUnit.SECONDS, "scheduleAtFixedRate : AsyncTask : 3");
//        
//        executor.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "scheduleWithFixedDelay : Runnable : 4");
//            }
//        }, 3, 1, TimeUnit.SECONDS);
//        
//        executor.scheduleWithFixedDelay(new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... params) {
//                Log.i(TAG, params[0]);
//                return null;
//            }
//        }, 3, 1, TimeUnit.SECONDS, "scheduleWithFixedDelay : AsyncTask : 4");
    }
}
