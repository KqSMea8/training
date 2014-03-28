/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import seker.common.BaseApplication;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class TrainingApplication extends BaseApplication {
    
    ActivityLifecycleCallbacks callbacks;
    HashMap<String, WeakReference<Activity>> activityMap;
//    private static final String TAG = "TestApp";
    
    @Override
    public void onCreate() {
        super.onCreate();
        // new Thread(new ProcessCompare(getApplicationContext())).start();
        // new Thread(new MemoryTest(getApplicationContext())).start();
        

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            activityMap = new HashMap<String, WeakReference<Activity>>();
            callbacks = new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivityCreated(activity=" + name + ")");
                    }
                    activityMap.put(activity.toString(), new WeakReference<Activity>(activity));
                }
                @Override
                public void onActivityStarted(Activity activity) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivityStarted(activity=" + name + ")");
                    }
                }
                @Override
                public void onActivityResumed(Activity activity) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivityResumed(activity=" + name + ")");
                    }
                }
                @Override
                public void onActivityPaused(Activity activity) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivityPaused(activity=" + name + ")");
                    }
                }
                @Override
                public void onActivityStopped(Activity activity) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivityStopped(activity=" + name + ")");
                    }
                }
                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivitySaveInstanceState(activity=" + name + ")");
                    }
                }
                @Override
                public void onActivityDestroyed(Activity activity) {
                    String name = activity.getClass().getSimpleName();
                    if (LOG) {
                        Log.d(TAG, "ActivityLifecycleCallbacks.onActivityDestroyed(activity=" + name + ")");
                    }
                    activityMap.remove(activity.toString());
                }
            };
            
            registerActivityLifecycleCallbacks(callbacks);
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
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            unregisterActivityLifecycleCallbacks(callbacks);
        }
    }
}
