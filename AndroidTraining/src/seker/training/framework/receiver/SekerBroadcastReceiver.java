/*
 * Copyright (C) 2014 XXX Inc. All rights reserved.
 */
package seker.training.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author xinjian.lxj
 *
 */
public class SekerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int index = intent.getIntExtra(action, -1);
        Log.w(action, "Receive a Broadcast: index=" + index);
    }
}
