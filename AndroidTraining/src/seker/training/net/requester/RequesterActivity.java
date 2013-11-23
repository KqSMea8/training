/*
 * Copyright (C) 2013 Seker Inc. All rights reserved.
 */
package seker.training.net.requester;

import android.os.Bundle;
import seker.common.BaseActivity;
import seker.common.BaseApplication;

/**
 * 
 * @author liuxinjian
 * @since 2013年11月23日
 */
public class RequesterActivity extends BaseActivity {
    
    public static final String TAG = "net_simple";
    
    public static final boolean LOG = BaseApplication.GLOBAL_LOG & true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
