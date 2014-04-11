/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework;

import seker.common.BaseActivity;
import seker.common.utils.ExceptionUtils;
import seker.training.framework.dummy.ActivityA;
import seker.training.framework.dummy.ActivityB;
import android.content.Intent;
import android.os.Bundle;
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
        
        // ExceptionUtils.crash();
        ExceptionUtils.anr();

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
