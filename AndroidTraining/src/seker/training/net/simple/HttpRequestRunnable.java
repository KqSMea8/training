/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.net.simple;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import seker.common.utils.StreamUtils;

import com.baidu.android.common.logging.Log;

/**
 * 
 * @author seker
 * @since 2013年11月22日
 */
public class HttpRequestRunnable implements Runnable {
    
    public static final String TAG = SimpleRequestActivity.TAG;
    
    public static final boolean LOG = SimpleRequestActivity.LOG;

    @Override
    public void run() {
        
        DefaultHttpClient client = new DefaultHttpClient(); 
        // 设置代理服务器地址和端口      
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
        HttpUriRequest method = new HttpGet("http://java.sun.com");
        //使用POST方法
        //HttpMethod method = new PostMethod("http://java.sun.com");
        try {
            HttpResponse response = client.execute(method);

            //打印服务器返回的状态
            if (LOG) {
                Log.d(TAG, response.getStatusLine() + "");
            }
            
            //打印返回的信息
            if (LOG) {
                String str = StreamUtils.streamToString(response.getEntity().getContent());
                Log.d(TAG, str);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
