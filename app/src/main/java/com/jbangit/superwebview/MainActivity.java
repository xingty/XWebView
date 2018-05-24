package com.jbangit.superwebview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jbangit.xwebview.XWebChromeClient;
import com.jbangit.xwebview.XWebView;
import com.jbangit.xwebview.XWebViewClient;
import com.jbangit.xwebview.bridge.DocumentBoundsEventHandler;
import com.jbangit.xwebview.bridge.ImageEventHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private XWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String link = "http://jijian.jbangit.cn/app/news/5/detail_web";
        webView = findViewById(R.id.webview);
        webView.initDefaultSettings();
        webView.setWebViewClient(new XWebViewClient());

        final ImageEventHandler handler = new ImageEventHandler(this) {
            @Override
            public void onImageClick(List<String> urls, String url, int index) {
                Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            }
        };
        webView.addEventHandler(handler);
        webView.addEventHandler(new DocumentBoundsEventHandler() {
            @Override
            public void onResize(int width, int height) {
                Toast.makeText(getApplicationContext(),"width:" + width + "  height:" + height,Toast.LENGTH_LONG).show();
            }
        });
        webView.setWebChromeClient(new XWebChromeClient());
        webView.loadUrl(link);
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
