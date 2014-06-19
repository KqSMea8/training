/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework.dummy;

import seker.common.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author xinjian.lxj
 *
 */
public class ActivityA extends BaseActivity {
    public ActivityA() {
        super();
        Log.e(TAG, "ActivityA()");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
        Bundle mExtras = intent.getBundleExtra("mExtras");
        if (mExtras != null) {
            intent.replaceExtras(mExtras);
        }
        
        boolean b = intent.getBooleanExtra("boolean", false);
        Log.i("seker", "boolean=" + b);
        
        int i = intent.getIntExtra("int", 20002);
        Log.i("seker", "int=" + i);
        
        String s = intent.getStringExtra("string");
        Log.i("seker", "string=" + s);
        
        // startActivity(new Intent(this, ActivityB.class));
    }
}
