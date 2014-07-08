/*
 * Copyright (C) 2011 Seker Inc. All rights reserved.
 */
package seker.training.net.requester;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import seker.common.BaseApplication;
import seker.common.Utility;
import seker.common.utils.StreamUtils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.baidu.android.common.logging.Log;
import com.baidu.android.common.net.ProxyHttpClient;

/**
 * 封装DefaultHttpClient类，该类的核心接口requestAsync()的主要功能为：
 * 1、异步化网络请求：requestAsync()方法体内，将启动一个子线程来发起网络请求。 
 * 2、接口参数化，以实现不同HTTP请求的扩展性。 
 * 3、解除
 *      a.网络请求 
 *      b.网络请求结果的解析 
 *      c.网络请求结果的处理（如：数据逻辑的处理、UI的处理）
 *  这三个子模块的耦合度。以实现网络请求功能的稳定性和可重用性。
 * 
 * @author liuxinjian
 * @since 2012-7-22
 * @param <R>
 *            HTTP请求结果所对应的数据类型(Response)，它将由IResponseParser解析，生成实例，
 *            然后传递给IResponseHandler处理。
 */
public class HttpRequester<R> {
    
    /** Log的TAG */
    private static final String TAG = "HttpRequester";
    
    public static final boolean DEBUG = true;
    
    /** File buffer stream size. */
    public static final int FILE_STREAM_BUFFER_SIZE = 8192;
    
    /** 运行上下文 */
    protected Context mContext;
    
    /** 用于取消此次网络请求 */
    protected volatile boolean mCancel = false;

    /**
     * 构造方法
     * 
     * @param context
     *            程序运行上下文
     */
    public HttpRequester(Context context) {
        mContext = context;
    }
    
    /**
     * 获取活动的连接。
     * 
     * @param context
     *            context
     * @return 当前连接
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return null;
        }
        return connectivity.getActiveNetworkInfo();
    }
    
    /**
     * 网络是否可用。
     * 
     * @param context
     *            context
     * @return 连接并可用返回 true
     */
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        // return networkInfo != null && networkInfo.isConnected();
        boolean flag = networkInfo != null && networkInfo.isAvailable();
        if (DEBUG) {
            Log.d(TAG, "isNetworkConnected, rtn: " + flag);
        }
        return flag;
    }
    
    /**
     * 创建一个 http client。
     * 
     * @param context
     *            Context.
     * @return ProxyHttpClient
     */
    public static ProxyHttpClient createHttpClient(Context context) {
        ProxyHttpClient httpclient = new ProxyHttpClient(context);
        // httpclient.getParams().setParameter("Accept-Encoding", "gzip");
        
        final int httpTimeout = 30000;
        final int socketTimeout = 50000;
        HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),
                httpTimeout);
        HttpConnectionParams
                .setSoTimeout(httpclient.getParams(), socketTimeout);
        return httpclient;
    }

    /**
     * 同步的发起HTTP请求。耗时操作， 不要在UI线程上调用它。
     * 
     * @param info
     *            HTTP请求信息，如：url、http请求类型、超时时长等
     * 
     * @param params
     *            该HTTP请求的参数
     *            
     * @param parser
     *            网络请求结果的解析器
     *            
     * @param handler
     *            网络请求结果的处理器
     *           
     * @param threadHanlder
     *            发起网络请求的原始线程的Handler
     */
    private void request(HttpRequestInfo info, List<ParamPair<?>> params, IResponseParser<InputStream, R> parser, 
            IResponseHandler<R> handler, RequestThreadHanlder<R> threadHanlder) {
        if (mCancel) {
            return;
        }
        
        int status = HttpStatus.SC_NOT_FOUND;
        InputStream result = null;
        List<ParamPair<String>> responseHeaders = null;
        ProxyHttpClient client = null;
        try {
            // 定义HTTP请求
            HttpUriRequest request = null;
            String url = info.url();
            if (DEBUG) {
                Log.d(TAG, "Org url:" + url);
            }
            switch (info.type()) {
            case HttpRequestInfo.HTTP_PUT:
                HttpPut put = new HttpPut(url);
                HttpEntity putEntity = processHttpPutParams(params);
                put.setEntity(putEntity);
                request = put;
                break;
            case HttpRequestInfo.HTTP_POST:
                HttpPost post = new HttpPost(url);
                HttpEntity postEntity = processHttpPostParams(params);
                post.setEntity(postEntity);
                request = post;
                break;
            case HttpRequestInfo.HTTP_GET:
                url = processHttpGetParams(url, params);
                HttpGet httpget = new HttpGet(url);
                request = httpget;
                break;
            default:
                throw new RuntimeException("Only support the HTTP_POST & HTTP_GET & HTTP_PUT request now.");
            }

            if (DEBUG) {
                Log.d(TAG, "Des url:" + url);
                StringBuilder builder = new StringBuilder("params: ");
                if (null != params && !params.isEmpty()) {
                    for (ParamPair<?> param : params) {
                        builder.append(param.toString()).append(", ");
                    }
                    builder.delete(builder.length() - 2, builder.length());
                } else {
                    builder.append("null");
                }
                Log.d(TAG, builder.toString());
            }

            // 客户端不要请求"gzip"压缩，因为服务端不一定支持gzip
            // request.setHeader("Accept-Encoding", "gzip");

            List<ParamPair<?>> headers = info.getHeaders();
            if (null != headers && !headers.isEmpty()) {
                if (DEBUG) {
                    StringBuilder builder = new StringBuilder("headers: ");
                    for (ParamPair<?> header : headers) {
                        builder.append(header.toString()).append(", ");
                    }
                    builder.delete(builder.length() - 2, builder.length());
                    Log.d(TAG, builder.toString());
                }
                
                for (ParamPair<?> header : headers) {
                    request.addHeader(header.getName(), header.getValue());
                }
            }
            
            if (mCancel) {
                return;
            }
            // 执行请求
            client = createHttpClient(mContext);

            // ConnManagerParams.setTimeout(client.getParams(), info.time());
            HttpConnectionParams.setConnectionTimeout(client.getParams(), info.time()); //设置连接超时时间
            HttpConnectionParams.setSoTimeout(client.getParams(), info.time() / 2);         //设置读取超时时间
        
            /**
             * 为了解决新浪短网址服务的HTTPS请求，No peer certificate的问题， 客户端需要信任新浪服务端的证书。
             * 该处仅为解决No peer certificate的问题而做出的妥协。
             */
            if (url.startsWith(HttpRequestInfo.PREFIX_HTTPS)) {
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);  
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                Scheme https = new Scheme(HttpRequestInfo.PREFIX_HTTPS, sf, HttpRequestInfo.PORT_HTTPS);
                client.getConnectionManager().getSchemeRegistry().register(https);
            }
            
            if (mCancel) {
                return;
            }
            HttpResponse response = client.executeSafely(request);
            if (null != response) {
                status = response.getStatusLine().getStatusCode();
                
                Header[] hs = response.getAllHeaders();
                if (null != hs && hs.length > 0) {
                    responseHeaders = new ArrayList<ParamPair<String>>(hs.length);
                    for (Header header : hs) {
                        responseHeaders.add(new ParamPair<String>(header.getName(), header.getValue()));
                    }
                }
                
                HttpEntity resEntity = response.getEntity();
                result = getInputStream(resEntity);
            }
        } catch (Exception e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            // 这里可能存在异常，为了保证HttpClient一定能够关闭，所以要catch异常。
            try {
                processHttpResponse(info, parser, handler, threadHanlder, status, responseHeaders, result);
            } catch (Exception e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
            
            if (null != client) {
                client.close();
            }
        }
    }
    
    /**
     * 拼接HttpGet请求的参数
     * 
     * @param url       IHttpRequestInfo提供的url
     * @param params    参数
     * @return          处理后的url
     */
    protected String processHttpGetParams(String url, List<ParamPair<?>> params) {
        if (null != params && !params.isEmpty()) {
            StringBuilder builder = new StringBuilder(url);
            if (!url.contains("?")) {
                ParamPair<?> param = params.remove(0);
                try {
                    builder
                    .append('?')
                    .append(URLEncoder.encode(param.getName(), HTTP.UTF_8))
                    .append('=')
                    .append(URLEncoder.encode(param.getValue(), HTTP.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    if (DEBUG) {
                        e.printStackTrace();
                    }
                }
            }
            for (ParamPair<?> param : params) {
                try {
                    builder
                    .append('&')
                    .append(URLEncoder.encode(param.getName(), HTTP.UTF_8))
                    .append('=')
                    .append(URLEncoder.encode(param.getValue(), HTTP.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    if (DEBUG) {
                        e.printStackTrace();
                    }
                }
            }
            url = builder.toString();
        }
        return url;
    }
    
    /**
     * 处理HttPut请求的参数
     * 
     * @param params    参数
     * @return          处理后的HttpEntity
     */
    protected HttpEntity processHttpPutParams(List<ParamPair<?>> params) {
        UrlEncodedFormEntity entity = null;
        if (null != params && !params.isEmpty()) {
            try {
                entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }
    
    /**
     * 处理HttPost请求的参数
     * 
     * @param params    参数
     * @return          处理后的HttpEntity
     */
    protected HttpEntity processHttpPostParams(List<ParamPair<?>> params) {
        UrlEncodedFormEntity entity = null;
        if (null != params && !params.isEmpty()) {
            try {
                entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }
    
    /**
     * 同步的发起HTTP请求。耗时操作，建议不要在UI线程上调用它。
     * 
     * @param info
     *            HTTP请求信息，如：url、http请求类型、超时时长等
     * 
     * @param parser
     *            网络请求结果的解析器
     *            
     * @param handler
     *            网络请求结果的处理器
     *            
     * @param threadHanlder
     *            发起网络请求的原始线程的Handler
     *
     * @param status 
     *            返回结果状态码
     *            
     * @param headers
     *            HTTP返回头信息
     *          
     * @param is 
     *            返回结果:Stream类型
     */
    @SuppressWarnings("unchecked")
    protected void processHttpResponse(HttpRequestInfo info, IResponseParser<InputStream, R> parser, 
            IResponseHandler<R> handler, RequestThreadHanlder<R> threadHanlder, int status, 
            List<ParamPair<String>> headers, InputStream is) {
        // 解析返回结果
        R response = null;
        if (null == is) {
            if (DEBUG) {
                Log.d(TAG, "(0) result=null");
            }
        } else {
            if (DEBUG && !info.url.endsWith(".zip")) {
                String s = StreamUtils.streamToString(is);
                Log.d(TAG, "(1) result=" + s);
                is = new java.io.ByteArrayInputStream(s.getBytes());
            }
            if (null == parser) {
                if (DEBUG) {
                    Log.d(TAG, "parser=null");
                }
                try {
                    response = (R) is;
                } catch (Exception e) {
                    if (DEBUG) {
                        Log.e(TAG, "InputStream can't cast class to R");
                        e.printStackTrace();
                    }
                }
            } else {
                response = parser.parseResponse(is);
                if (DEBUG) {
                    Log.d(TAG, "(response = mParser.parseResponse(result)) = "
                            + ((null == response) ? "null" : response.toString()));
                }
            }
        }

        if (mCancel) {
            return;
        }
        if (null != threadHanlder) {    // 将结果发送给父进程处理
            Message msg = Message.obtain(threadHanlder, 0, new ResponseData<R>(status, headers, response));
            threadHanlder.sendMessage(msg);
        } else if (null != handler) { // 处理返回结果
            if (DEBUG) {
                Log.d(TAG, "handleMessage(info=" + info + ", status=" + status + ", response=" + response + ")");
            }
            handler.onResult(info, status, headers, response);
        }
    }

    /**
     * server如果下发为gip，则获取gzip inputstream .
     * 
     * @param resEntity
     *            {@link HttpEntity}
     * @return InputStream or GZIPInputStream or null
     * @throws IllegalStateException 
     *             {@link IllegalStateException}
     * @throws IOException
     *             {@link IOException}
     */
    protected InputStream getInputStream(HttpEntity resEntity) throws IllegalStateException, IOException {
        InputStream result = null;
        
        Header header = resEntity.getContentEncoding();
        if (header != null) {
            String contentEncoding = header.getValue();
            if (contentEncoding.toLowerCase().indexOf("gzip") != -1) {
                try {
                    result = new GZIPInputStream(resEntity.getContent());
                } catch (Exception e) {
                    if (DEBUG) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        if (null == result) {
            result = resEntity.getContent();
        }
        
        return result;
    }
    
    /**
     * 网络HTTP请求异步化，这个函数调用后会立即返回，它会启动一个子线程去执行耗时的网络操作。
     * 注意：这个方法内会创建{@link android.os.Hanlder},因此调用线程必须保证本身已经初始化了消息队列(Loop.prepare())
     * 
     * @param info
     *            HTTP请求信息，如：url、http请求类型、超时时长等
     * 
     * @param list
     *            该HTTP请求的参数
     * 
     * @param parser
     *            该HTTP请求结果的解析器，返回结果回调给调用者自行解析
     * 
     * @param handler
     *            该HTTP请求结果的处理器，返回结果回调给调用者处理
     */
    public void requestAsync(final HttpRequestInfo info, final List<ParamPair<?>> list,
            final IResponseParser<InputStream, R> parser, final IResponseHandler<R> handler) {
        mCancel = false;
        final RequestThreadHanlder<R> threadHanlder = new RequestThreadHanlder<R>(info, handler);
        if (isNetworkConnected(mContext)) {
            Utility.newThread(new Runnable() {
                @Override
                public void run() {
                    if (mCancel) {
                        return;
                    }
                    request(info, list, parser, null, threadHanlder);
                }
            }, "HttpRequester: " + info.url()).start();
        } else {
            if (DEBUG) {
                Log.d(TAG, "HttpRequester.requestAsync(Network is not connected):" + info);
            }
            handler.onResult(info, IResponseHandler.NO_NETWORK, null, null);
        }
    }
    
    /**
     * 网络HTTP请求同步化
     * @param info
     *            HTTP请求信息，如：url、http请求类型、超时时长等
     * 
     * @param list
     *            该HTTP请求的参数
     * 
     * @param parser
     *            该HTTP请求结果的解析器，返回结果回调给调用者自行解析
     * 
     * @param handler
     *            该HTTP请求结果的处理器，返回结果回调给调用者处理
     */
    public void requestSync(final HttpRequestInfo info, final List<ParamPair<?>> list,
            final IResponseParser<InputStream, R> parser, final IResponseHandler<R> handler) {
        mCancel = false;
        if (isNetworkConnected(mContext)) {
            request(info, list, parser, handler, null);
        } else {
            if (DEBUG) {
                Log.d(TAG, "HttpRequester.requestSync(Network is not connected):" + info);
            }
            handler.onResult(info, IResponseHandler.NO_NETWORK, null, null);
        }
    }
    
    /**
     * 取消网络请求：同时适用于requestAsync()和requestSync()发起的请求
     */
    public void cancel() {
        mCancel = true;
    }
    
    /**
     * 由于使用AsyncTask，在网络不佳的情况下会阻塞浏览器模块的AsyncTask的执行，所以改用Thread + Handler的方式来替代AsyncTask
     * 
     * @author liuxinjian
     * @since 2012-8-20
     */
    static class RequestThreadHanlder<R> extends Handler {
        /**
         * 该HTTP请求结果的处理器，返回结果回调给调用者处理（它会在UI线程上被invoke）
         */
        private final IResponseHandler<R> mHandler;
        
        /**
         * HTTP请求信息，如：url、http请求类型、超时时长等
         */
        private final HttpRequestInfo mInfo;
        
        /**
         * 构造方法
         * 
         * @param info      HTTP请求信息，如：url、http请求类型、超时时长等
         * @param handler   该HTTP请求结果的处理器，返回结果回调给调用者处理
         */
        public RequestThreadHanlder(HttpRequestInfo info, IResponseHandler<R> handler) {
            mHandler = handler;
            mInfo = info;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            // 处理返回结果
            if (null != mHandler) {
                ResponseData<R> data = (ResponseData<R>) msg.obj;
                if (null != data) {
                    if (DEBUG) {
                        Log.d(TAG, "handleMessage(info=" + mInfo + "data=" + data.toString() + ")");
                    }
                    mHandler.onResult(mInfo, data.status, data.headers, data.response);
                } else {
                    if (DEBUG) {
                        Log.e(TAG, "handleMessage(ResponseData<R> data == null)");
                    }
                }
            } else {
                if (DEBUG) {
                    Log.e(TAG, "handleMessage(mHandler == null)");
                }
            }
        }
    }
    
    /**
     * HTTP请求的返回数据：状态；返回Header头；Response
     * 
     * @author liuxinjian
     * @since 2013-6-3
     * @param <R> HTTP请求结果所对应的数据类型(Response)
     */
    static class ResponseData<R> {
        /** HTTP请求的返回状态码 */
        int status;
        
        /** 返回Header头 */
        List<ParamPair<String>> headers;
        
        /** HTTP请求结果所对应的数据类型(Response) */
        R response;

        /**
         * 构造方法
         * @param sta       HTTP请求返回状态码
         * @param hds       返回Header头
         * @param r         HTTP请求结果所对应的数据类型(Response)
         */
        public ResponseData(int sta, List<ParamPair<String>> hds, R r) {
            status = sta;
            headers = hds;
            response = r;
        }

        @Override
        public String toString() {
            StringBuilder strHeaders = null;
            if (null != headers && !headers.isEmpty()) {
                strHeaders = new StringBuilder("{");
                for (ParamPair<String> header : headers) {
                    strHeaders.append("[").append(header).append("],");
                }
                strHeaders.deleteCharAt(strHeaders.length() - 1);
                strHeaders.append("}");
            }
            return "ResponseData [status=" + status + ", headers=" + strHeaders + ", response=" + response + "]";
        }
    }
}
