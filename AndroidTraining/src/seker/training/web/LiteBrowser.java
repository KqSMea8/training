package seker.training.web;

import seker.common.BaseActivity;
import seker.common.utils.LogUtils;
import seker.training.R;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * WebView Actiivity
 * 
 * @author seker
 * @since 2013-12-1
 */
@SuppressLint("SetJavaScriptEnabled")
public class LiteBrowser extends BaseActivity {
    
    private WebView mWebView; 
    
    private TextView mTitleTxtView;
    
    private ImageView mBackword;
    private ImageView mForword;
    private ImageView mRefresh;
    private ImageView mStop;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.litebrowser);
        findViewById(R.id.back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleTxtView = (TextView) findViewById(R.id.title);
        
        mBackword = (ImageView) findViewById(R.id.backward);
        mBackword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                }
            }
        });
        
        mForword = (ImageView) findViewById(R.id.forward);
        mForword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoForward()) {
                    mWebView.goForward();
                }
            }
        });
        
        mRefresh = (ImageView) findViewById(R.id.refresh);
        mRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
            }
        });
        
        mStop = (ImageView) findViewById(R.id.stop);
        mStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.stopLoading();
            }
        });
        
        mWebView = (WebView) findViewById(R.id.webview);
        
//        String summary = "dimly<body>You scored <b>192</b> points.</body></html>";
//        webview.loadData(summary, "text/html", null);
        
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(mWebView, url, favicon);
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                mWebView.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                super.onPageFinished(mWebView, url);
            }
            @Override
            public void onLoadResource(WebView view, String url) {
                if (LOG) {
                    //Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                super.onLoadResource(mWebView, url);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
                super.onReceivedError(mWebView, errorCode, description, failingUrl);
            }
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
                super.onScaleChanged(mWebView, oldScale, newScale);
            }
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                super.doUpdateVisitedHistory(mWebView, url, isReload);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (LOG) {
                    //Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", newProgress=" + newProgress);
                }
                super.onProgressChanged(mWebView, newProgress);
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", title=" + title);
                }
                mTitleTxtView.setText(title);
                super.onReceivedTitle(mWebView, title);
            }
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                    super.onReceivedIcon(view, icon);
                }
            }
            
        });
        
        mWebView.loadUrl("http://www.sohu.com/");
    }

}
