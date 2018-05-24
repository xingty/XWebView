package com.jbangit.xwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jbangit.xwebview.bridge.JSBridgeHandler;

public class XWebView extends WebView{
    private XWebViewClient client;

    public XWebView(Context context) {
        super(context);
    }

    public XWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("JavascriptInterface")
    public void addEventHandler(JSBridgeHandler handler) {
        addJavascriptInterface(handler,handler.getName());
        client.addHandler(handler);
    }

    public void removeEventHandler(JSBridgeHandler handler) {
        client.removeHandler(handler);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        if (!(client instanceof XWebViewClient)) {
            throw new RuntimeException("you must be set XWebViewClient to XWebView");
        }

        this.client = (XWebViewClient) client;
        super.setWebViewClient(client);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initDefaultSettings() {
        WebView.setWebContentsDebuggingEnabled(true);
        WebSettings webSettings = getSettings();
        webSettings.setDefaultFontSize(16);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
    }

    @Override
    public void destroy() {
        client.removeHandlers();
        client = null;
        super.destroy();
    }

    public static void startFileChooser(Activity activity, int requestCode) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "Image Chooser"), requestCode);
    }
}
