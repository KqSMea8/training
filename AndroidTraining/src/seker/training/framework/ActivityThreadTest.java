/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

import seker.common.BaseActivity;
import seker.training.framework.dummy.ActivityA;
import seker.training.framework.dummy.ActivityB;
import android.app.Activity;
import android.content.Intent;
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
            }
        });
        
        startActivity(new Intent(this, ActivityA.class));
        startActivity(new Intent(this, ActivityB.class));
    }
}
