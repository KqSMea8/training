/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training;

import seker.common.BaseApplication;
import android.content.Context;
import android.content.res.Configuration;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class TrainingApplication extends BaseApplication {
    
    protected static final String TAG = "TrainingApplication";
    
    @Override
    public void onCreate() {
        super.onCreate();
//        new Thread(new seker.training.dataprocess.ProcessCompare(getApplicationContext())).start();
//        new Thread(new seker.training.memory.MemoryTest(getApplicationContext())).start();
//        new Thread(new seker.training.reflect.Log.LogTest()).start();
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
