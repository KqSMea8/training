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
        
        // startActivity(new Intent(this, ActivityB.class));
    }
}
