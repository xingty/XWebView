package com.jbangit.superwebview;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewUtils {
    @SuppressLint("SetJavaScriptEnabled")
    public static void initDefaultSettings(WebView webView) {
        WebView.setWebContentsDebuggingEnabled(true);
        WebSettings webSettings = webView.getSettings();
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
}
