/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

import seker.common.BaseActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author xinjian.lxj
 * 
 */
public class ActivityThreadTest extends BaseActivity {

    public static final String TAG = "ActivityThreadTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("Click Me");
        setContentView(button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                printAllActivities(ActivityThreadTest.this);
            }
        });
    }

    /**
     * 
     */
    private static void printAllActivities(Activity activity) {
        try {
            Class<?> clazz_Activity = Class.forName("android.app.Activity");
            Field field_mMainThread = clazz_Activity.getDeclaredField("mMainThread");
            field_mMainThread.setAccessible(true);
            Object mMainThread = field_mMainThread.get(activity);

            Class<?> clazz_ActivityThread = Class.forName("android.app.ActivityThread");
            Field field_mActivities = clazz_ActivityThread.getDeclaredField("mActivities");
            field_mActivities.setAccessible(true);
            Object mActivities = field_mActivities.get(mMainThread);

            HashMap<?, ?> map = (HashMap<?, ?>) mActivities;
            Collection<?> collections = map.values();
            if (null != collections && !collections.isEmpty()) {
                Class<?> clazz_ActivityClientRecord = Class.forName("android.app.ActivityThread$ActivityClientRecord");
                Field field_activitiy = clazz_ActivityClientRecord.getDeclaredField("activity");
                field_activitiy.setAccessible(true);

                Activity acti;
                for (Object obj : collections) {
                    acti = (Activity) field_activitiy.get(obj);
                    Log.d(TAG, "activity.name=" + acti.getClass().getName());
                    if (null != acti && !acti.isFinishing()) {
                        Log.d(TAG, "activity.name=" + acti.getClass().getName() + " not finish.");
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
