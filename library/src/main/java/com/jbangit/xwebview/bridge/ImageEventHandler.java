package com.jbangit.xwebview.bridge;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.jbangit.xwebview.utils.AssetsUtils;

import java.util.Arrays;
import java.util.List;

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

    public abstract void onImageClick(List<String> urls, String url, int index);
}
