package seker.training.web;

import seker.common.BaseActivity;
import seker.common.utils.LogUtils;
import seker.training.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebView Actiivity
 * 
 * @author seker
 * @since 2013-12-1
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.webview_demo);
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.baidu.com");  
                Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
                intent.setPackage(getPackageName());
                startActivity(intent);
            }
        });
        
        final WebView webview = (WebView) findViewById(R.id.webview);
        
//        String summary = "dimly<body>You scored <b>192</b> points.</body></html>";
//        webview.loadData(summary, "text/html", null);
        
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(webview, url, favicon);
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                webview.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                super.onPageFinished(webview, url);
            }
            @Override
            public void onLoadResource(WebView view, String url) {
                if (LOG) {
                    //Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                super.onLoadResource(webview, url);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
                super.onReceivedError(webview, errorCode, description, failingUrl);
            }
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                }
                super.onScaleChanged(webview, oldScale, newScale);
            }
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                }
                super.doUpdateVisitedHistory(webview, url, isReload);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (LOG) {
                    //Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", newProgress=" + newProgress);
                }
                super.onProgressChanged(webview, newProgress);
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", title=" + title);
                }
                super.onReceivedTitle(webview, title);
            }
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                if (LOG) {
                    Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                    super.onReceivedIcon(view, icon);
                }
            }
            
        });
        
        webview.loadUrl("http://www.sohu.com/");
    }

}
