package com.jbangit.xwebview;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class XWebChromeClient extends WebChromeClient{
    private ValueCallback<Uri> valueCallback;
    private ValueCallback<Uri[]> filePathCallback;

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        this.valueCallback = valueCallback;
        onOpenFileChooser();
    }

    //For Android  >= 4.1
    public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
        this.valueCallback = valueCallback;
        onOpenFileChooser();
    }

    // For Android >= 5.0
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.filePathCallback = filePathCallback;
        onOpenFileChooser();
        return true;
    }

    protected void onOpenFileChooser() {}

    public void processCallback(Intent intent) {
        if (valueCallback != null) {
            valueCallback.onReceiveValue(intent.getData());
            valueCallback = null;
            return;
        }

        if (filePathCallback != null) {
            processFilePathCallback(intent);
        }
    }

    private void processFilePathCallback(Intent intent) {
        Uri[] results = null;
        String dataString = intent.getDataString();
        ClipData clipData = intent.getClipData();

        if (clipData != null) {
            results = new Uri[clipData.getItemCount()];
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item item = clipData.getItemAt(i);
                results[i] = item.getUri();
            }
        }

        if (dataString != null) {
            results = new Uri[]{ Uri.parse(dataString) };
        }

        filePathCallback.onReceiveValue(results);
        filePathCallback = null;
    }

    public ValueCallback<Uri> getValueCallback() {
        return valueCallback;
    }

    public ValueCallback<Uri[]> getFilePathCallback() {
        return filePathCallback;
    }
}
