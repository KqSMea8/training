/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.dataprocess.xml.sax;

import java.io.IOException;
import java.io.InputStream;

import seker.common.BaseActivity;
import seker.training.TrainingApplication;
import seker.training.dataprocess.Channel;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author seker
 * @since 2013年11月9日
 */
public class SaxXmlActivity extends BaseActivity {
    
    public static final String TAG = "sax";
    
    public static final boolean LOG = TrainingApplication.GLOBAL_LOG & true;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            InputStream is = getAssets().open("data/data.xml");
            Channel channel = new SaxParser().parse(is);
            if (LOG) {
                Log.i(TAG, channel+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}