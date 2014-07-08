package seker.training.ui.web;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import seker.common.BaseActivity;
import seker.common.utils.LogUtils;
import seker.common.utils.TextUtils;
import seker.training.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
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
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                webview.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                super.onPageFinished(webview, url);
            }
            @Override
            public void onLoadResource(WebView view, String url) {
                //Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                super.onLoadResource(webview, url);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                super.onReceivedError(webview, errorCode, description, failingUrl);
            }
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                super.onScaleChanged(webview, oldScale, newScale);
            }
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", url=" + url);
                super.doUpdateVisitedHistory(webview, url, isReload);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", newProgress=" + newProgress);
                super.onProgressChanged(webview, newProgress);
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()) + ", title=" + title);
                super.onReceivedTitle(webview, title);
            }
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                Log.d(TAG, LogUtils.getClassFileLineMethod(getClass().getSimpleName()));
                super.onReceivedIcon(view, icon);
            }
            
        });
        
        webview.loadUrl("http://www.sohu.com/");
    }

    @Override
    protected void onDestroy() {
        WebView webview = new WebView(this);
        webview.clearCache(true);
        clearWebViewCacheIfNeed();
        super.onDestroy();
    }
    


    /**
     * 为了解决缓存在本地的数据会比较多的问题(Webview产生的cache文件约5M+),删除WebView所产生的cache文件
     * 
     * 具体优化策略为：程序退出时，将检测手机内存存储的剩余空间，当剩余空间小于100M时，主动清空cache目录下由Webview生成的目录。
     * 
     * /data/data/com.eg.android.AlipayGphone/cache/webviewCache[Chromium]/…
     */
    private void clearWebViewCacheIfNeed() {
        long start = System.currentTimeMillis();
        final int MB = 1048576;
        final int LIMIT = 100;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            int free = statFs.getAvailableBlocks() * statFs.getBlockSize() / MB;
            if (free > LIMIT) {
                // new WebView(mApplication).clearCache(true);
                clearWebViewCache();
            }
        } catch (Exception e) {
            Log.e("MicroApplicationContextImpl", "", e);
        }
        long end = System.currentTimeMillis();
        Log.i("MicroApplicationContextImpl", "cost " + (end - start) + " ms.");
    }

    /**
     * 删除/data/data/{packagename}/cache/下的WebView生成的Cache目录。
     * WebView生成的Cache目录在不同版本的Android中命名并不相同，目前已知的命名有：
     *  webviewCache
     *  webviewCacheChromium
     * 所以粗略制定判断规则为：删除名称中包含“webviewCache”关键字的文件/文件夹
     */
    private void clearWebViewCache() {
        File cache = getCacheDir().getAbsoluteFile();
        if (cache.exists()) {
            String[] files = cache.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return !TextUtils.isEmpty(filename) && filename.contains("webviewCache");
                }
            });
            if (null != files && files.length > 0) {
                for (String file : files) {
                    String command = new StringBuilder("rm -r -f ").append(cache).append(File.separatorChar)
                            .append(file).toString();
                    execCommand(command);
                }
            }
        }
    }

    public void execCommand(String command) {
        Log.d("MicroApplicationContextImpl", "command=" + command);
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
            
            // NOTE: test code, just keep it. Begin
//            Process proc = runtime.exec(commnd);
//            if (proc.waitFor() != 0) {
//                Log.d("MicroApplicationContextImpl", "exit value = " + proc.exitValue());
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                stringBuffer.append(line+"-");
//            }
//            Log.d("MicroApplicationContextImpl", stringBuffer.toString());
            // NOTE: test code, just keep it. END
        } catch (Exception e) {
            Log.e("MicroApplicationContextImpl", "", e);
        }
    }

    protected void usdCode() {
        File cache = getCacheDir().getAbsoluteFile();
        if (cache.exists()) {
            String[] files = cache.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return !TextUtils.isEmpty(filename) && filename.contains("webviewCache");
                }
            });
            if (null != files && files.length > 0) {
                for (String file : files) {
                    execCommand(new StringBuilder("rm -r -f ").append(cache).append(File.separatorChar).append(file).toString());
                }
            }
        }
    }

    protected void useShellCmd() {
        String cache = getCacheDir().getAbsolutePath();
        Log.d(TAG, "cache=" + cache);
        
        StringBuilder command = new StringBuilder("rm -r -f ").append(cache).append("/*webviewCache*");
                //.append("/webviewCacheChromium");
        
        execCommand(command.toString());
    }

    protected void useWebView() {
        WebView webview = new WebView(this);
        webview.getSettings().setSaveFormData(false);
        webview.clearView();
        webview.clearCache(true);
        webview.clearHistory();
        
        String path = webview.getSettings().getDatabasePath();
        Log.d(TAG, "getDatabasePath=" + path);
        
//        webview.getSettings().setAppCachePath("");
        
//        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webview.clearCache(true);
//        webview.clearCache(true);
//        webview.clearHistory();
//        webview.clearFormData();
//        getCacheDir().delete();
    }

    protected void useCacheManager() {
        try {
            Class<?> clazz = Class.forName("android.webkit.CacheManager");
            Object object = clazz.newInstance();
            
            Method[] methods = clazz.getMethods();
            if (null != methods && methods.length > 1) {
                for (Method md : methods) {
                    Log.d(TAG, md.getName());
                }
            }
            
            Method method = clazz.getMethod("getCacheFileBaseDir");
            File file = (File) method.invoke(object);
            Log.d(TAG, file.getAbsolutePath());
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
