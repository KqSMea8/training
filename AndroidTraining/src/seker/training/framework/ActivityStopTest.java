/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework;

import seker.common.BaseActivity;

/**
 * @author xinjian.lxj
 *
 */
public class ActivityStopTest extends BaseActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
