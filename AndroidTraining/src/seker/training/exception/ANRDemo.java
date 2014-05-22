/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.exception;

import seker.common.BaseActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author xinjian.lxj
 *
 */
public class ANRDemo extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RelativeLayout root = new RelativeLayout(this);
        setContentView(root);
        
        Button button = new Button(this);
        button.setText("Click me to ANR");
        root.addView(button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    Thread.sleep(DateUtils.MINUTE_IN_MILLIS * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
