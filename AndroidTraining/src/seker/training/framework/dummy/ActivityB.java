/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework.dummy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import seker.common.BaseActivity;
import seker.common.utils.ActivityUtils;
import android.os.Process;

/**
 * @author xinjian.lxj
 *
 */
public class ActivityB extends BaseActivity {
    
    public ActivityB() {
        super();
        Log.e(TAG, "ActivityB()");
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Button button = new Button(this);
        button.setText("Click Me");
        setContentView(button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishAllActivies(ActivityB.this);
                Process.killProcess(Process.myPid());
                System.exit(10);
            }
        });
    }

}
