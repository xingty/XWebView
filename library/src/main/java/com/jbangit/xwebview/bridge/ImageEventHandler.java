package com.jbangit.xwebview.bridge;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.jbangit.xwebview.utils.AssetsUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 监听WebView所有img标签点击事件,
 * 把点击的url返回到本地onImageClick
 */
public abstract class ImageEventHandler implements JSBridgeHandler{
    private String jsCode;

    public ImageEventHandler(Context context) {
        loadJSCode(context);
    }

    private void loadJSCode(Context context) {
        jsCode = AssetsUtils.readString(context,"js/img-event.js");
    }

    @Override
    public String getName() {
        return "imgEventHandler";
    }

    @JavascriptInterface
    public void onImageClick(String urls,String url,int index) {
        List<String> links = Arrays.asList(urls.split(","));
        onImageClick(links,url,index);
    }

    @Override
    public String getJSCode() {
        return jsCode;
    }

    /**
     * @param urls ArrayList<String>
     * @param url String
     * @param index index
     */
    public abstract void onImageClick(List<String> urls, String url, int index);
}
