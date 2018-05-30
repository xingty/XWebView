package com.jbangit.xwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jbangit.xwebview.bridge.JSBridgeHandler;

public class XWebView extends WebView{
    private XWebViewClient client;
    private XWebChromeClient webChromeClient;

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

    public void setWebChromeClient(XWebChromeClient client) {
        this.webChromeClient = client;
        super.setWebChromeClient(client);
    }

    @Override
    public void destroy() {
        client.removeHandlers();
        client = null;
        super.destroy();
    }

    /**
     * 处理FileChooser返回的数据
     * @param data Intent
     */
    public void processChooserCallback(Intent data) {
        if (webChromeClient != null) {
            webChromeClient.processCallback(data);
        }
    }

    public static void startFileChooser(Activity activity, int requestCode) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "Image Chooser"), requestCode);
    }
}
