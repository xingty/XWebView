package com.jbangit.xwebview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class XWebViewClient extends WebViewClient{
    private ArrayList<String> listOfJSCode = new ArrayList<>();

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("http")||url.startsWith("https")){
            return super.shouldOverrideUrlLoading(view,url);
        }

        return processSchema(view,url) || super.shouldOverrideUrlLoading(view,url);
    }

    /**
     * 当点击了其他的URLSchema,在这里统一处理
     * @param view WebView
     * @param url String
     * @return return true if consume
     */
    protected boolean processSchema(WebView view,String url) {
        if (url.startsWith("mqqwpa")) {
            return openApp(view.getContext(),url);
        } else if (url.startsWith("jbangit")) {
            return openApp(view.getContext(),url);
        }

        return false;
    }

    protected boolean openApp(Context context,String schema) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(schema));
            context.startActivity(intent);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (!listOfJSCode.isEmpty()) {
            executeJSCode(view);
        }
    }

    private void executeJSCode(WebView webView) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listOfJSCode.size(); i++) {
            sb.append(listOfJSCode.get(i));
            sb.append("\n");
        }

        String code = sb.toString();
        String jsCode = String.format("javascript:(function() { %s })();",code);
        webView.loadUrl(jsCode);
    }

    /**
     * 添加Javascript代码
     * @param code js code
     */
    public void addJSCode(String code) {
        listOfJSCode.add(code);
    }
}
