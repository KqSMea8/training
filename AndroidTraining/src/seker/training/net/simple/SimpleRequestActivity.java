/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.training.net.simple;

import seker.common.BaseActivity;
import seker.common.BaseApplication;
import android.os.Bundle;

/**
 * 
 * @author liuxinjian
 * @since 2013年11月23日
 */
public class SimpleRequestActivity extends BaseActivity {
    
    public static final String TAG = "net_simple";
    
    public static final boolean LOG = BaseApplication.GLOBAL_LOG & true;
            
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // new Thread(new UrlRequestRunnable()).start();
        new Thread(new HttpRequestRunnable()).start();
    }
}
