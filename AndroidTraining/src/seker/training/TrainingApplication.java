/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training;

import seker.common.BaseApplication;
import seker.training.dataprocess.ProcessCompare;
import android.content.Context;
import android.content.res.Configuration;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class TrainingApplication extends BaseApplication {
    
    @Override
    public void onCreate() {
        super.onCreate();
        // new Thread(new ProcessCompare(getApplicationContext())).start();
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
