/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.net.simple;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import seker.common.utils.StreamUtils;

import com.baidu.android.common.logging.Log;

/**
 * 
 * @author seker
 * @since 2013年11月22日
 */
public class UrlRequestRunnable implements Runnable {
    
    public static final String TAG = SimpleRequestActivity.TAG;
    
    @Override
    public void run() {
        try {
            URL url = new URL("http://news.163.com/special/00011K6L/rss_newstop.xml");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            
            urlConn.setRequestMethod("GET/POST/PUT");
            urlConn.setIfModifiedSince(87665);
            urlConn.setConnectTimeout(45214);
            urlConn.setReadTimeout(4321);
            urlConn.setRequestProperty("", "");
            urlConn.setRequestProperty("", "");
            urlConn.setRequestProperty("", "");
            urlConn.setRequestProperty("", "");
            
            // 设置输入和输出流 s
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            // 设置请求方式为POST
            urlConn.setRequestMethod("GET");
            // POST请求不能使用缓存
            urlConn.setUseCaches(false);
            
            InputStream in = urlConn.getInputStream();
            String str = StreamUtils.streamToString(in);
            
            Log.d(TAG, str);
            
            StreamUtils.closeSafely(in);
            
            // 关闭连接
            urlConn.disconnect();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
