/*
 * Copyright (C) 2013 Seker. All rights reserved.
 */
package seker.training.net.simple;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import seker.common.utils.StreamUtils;

import com.baidu.android.common.logging.Log;

/**
 * 
 * @author seker
 * @since 2013年11月22日
 */
public class HttpRequestRunnable implements Runnable {
    
    public static final String TAG = SimpleRequestActivity.TAG;
    
    @Override
    public void run() {
        
        DefaultHttpClient client = new DefaultHttpClient(); 
        HttpParams param = client.getParams();
        param.getIntParameter("", 0);
        param.setLongParameter("", 0);
        
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
        //HttpGet get = new HttpGet("http://java.sun.com");
        
        HttpPost post = new HttpPost("http://java.sun.com");
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("", ""));
        try {
            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(entity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        
        try {
            HttpResponse response = client.execute(post);

            //打印服务器返回的状态
            Log.d(TAG, response.getStatusLine() + "");
            
            //打印返回的信息
            String str = StreamUtils.streamToString(response.getEntity().getContent());
            Log.d(TAG, str);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
