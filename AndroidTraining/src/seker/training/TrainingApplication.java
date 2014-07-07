/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

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
    /**
     * Activity Lifecycle Callbacks
     */
    private ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
    
    HashMap<String, WeakReference<Activity>> activityMap;
    private static final String TAG = "TestApp";
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        Map<String, String> map = new ConcurrentHashMap<String, String>();
//        map.put("1111", "1111");
//        map.put("2222", "2222");
//        map.put("3333", "3333");
//        map.put("4444", "4444");
//        map.put("5555", "5555");
        
        Set<String> set = null;//map.keySet();
        String json = JSON.toJSONString(set);
        Log.d(TAG, json);
        String json2 = set2Json(set);
        Log.d(TAG, json2);
        
        json = "";
        
//        Set<String> set2 = JSON.parseObject(json, new TypeReference<Set<String>>(){});
//        for (String str : set2) {
//            Log.d(TAG, str);
//        }
        Set<String> set3 = json2Set(json);
//        for (String str : set3) {
//            Log.d(TAG, str);
//        }
        
        // new Thread(new seker.training.dataprocess.ProcessCompare(getApplicationContext())).start();
        // new Thread(new MemoryTest(getApplicationContext())).start();
        // new Thread(new LogTest()).start();
        
        registerActivityCallbacks();
    }
    
    private String set2Json(Set<String> set) {
        String strRet = "null";
        if (null != set) {
            JSONArray jsonArray = new JSONArray();
            if (!set.isEmpty()) {
                for (String str : set) {
                    jsonArray.put(str);
                }
            }
            strRet = jsonArray.toString();
        }
        return strRet;
    }
    
    private Set<String> json2Set(String json) {
        Set<String> setRet = null;
        if (null != json) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                final int N = jsonArray.length();
                setRet = new HashSet<String>(N);
                for (int i = 0; i < N; i ++) {
                    setRet.add(jsonArray.get(i).toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return setRet;
    }

    void registerActivityCallbacks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            activityMap = new HashMap<String, WeakReference<Activity>>();
            mActivityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    // Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
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
            
            registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
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
        
        unregisterActivityCallbacks();
    }

    void unregisterActivityCallbacks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        }
    }
}
