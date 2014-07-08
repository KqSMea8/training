/*
 * Copyright (C) 2013 Seker Inc. All rights reserved.
 */
package seker.training.net.requester;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import seker.common.BaseActivity;
import seker.training.dataprocess.Channel;
import seker.training.dataprocess.json.fast.FastJsonParser;
import android.os.Bundle;

/**
 * 
 * @author liuxinjian
 * @since 2013年11月23日
 */
public class RequesterActivity extends BaseActivity {
    
    public static final String TAG = "net_simple";
    
    HttpRequester<Channel> requester = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        HttpRequestInfo info = new HttpRequestInfo("http://www.baidu.com", 
                HttpRequestInfo.HTTP_POST, 5, HttpRequestInfo.AUTH_NONE);
        
        requester = new HttpRequester<Channel>(getApplicationContext());
        
        List<ParamPair<?>> list = new ArrayList<ParamPair<?>>();
        list.add(new ParamPair<Long>("", 9L));
        list.add(new ParamPair<String>("", ""));
        
        IResponseParser<InputStream, Channel> parser = new IResponseParser<InputStream, Channel>() {
            @Override
            public Channel parseResponse(InputStream result) {
                return new FastJsonParser().parse(result);
            }
        };
        
        IResponseHandler<Channel> handler = new IResponseHandler<Channel>() {
            @Override
            public void onResult(HttpRequestInfo info, int status, List<ParamPair<String>> headers, Channel response) {
                
            }
        };
        
        requester.requestAsync(info, list, parser, handler);
    }
    
    @Override
    protected void onDestroy() {
        if (null != requester) {
            requester.cancel();
        }
        super.onDestroy();
    }
}
