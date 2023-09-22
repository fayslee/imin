package com.imin.js.electronic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.imin.js.electronic.R;
import com.neostra.electronic.Electronic;
import com.neostra.electronic.ElectronicCallback;

import android.util.Log;

import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity implements ElectronicCallback {
    private WebView mWebView;
    private Context mContext;
    private Electronic mElectronic;
    String TAG="MainActivity === lsy====";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext =this;
        this.mWebView =  (WebView)findViewById(R.id.webview);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.addJavascriptInterface(this, "JsActivity");
        doWebViewPrint();
        mElectronic = new Electronic.Builder().setDevicePath("/dev/ttyS4").setReceiveCallback(this).builder();

    }


    public void doWebViewPrint(){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e(TAG,"onPageFinished");
            }
        });
        mWebView.loadUrl("file:///android_asset/index.html");
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void
    callJS(String info, String key){
        mWebView.loadUrl("javascript:electronicStatus('"+info+"','"+key+"')");
    }

    @Override
    public void electronicStatus(String info, String key) {
        final String info2 =info;
        final String key2 = key;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callJS(info2,key2);
            }
        });
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @JavascriptInterface
    public void turnZero(){
        mElectronic.turnZero();
    }
    @JavascriptInterface
    public void removePeel(){
        mElectronic.removePeel();
    }
}