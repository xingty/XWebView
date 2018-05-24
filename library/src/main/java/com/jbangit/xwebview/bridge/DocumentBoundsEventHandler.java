package com.jbangit.xwebview.bridge;

import android.webkit.JavascriptInterface;

/**
 * 获取Document宽高
 */
public abstract class DocumentBoundsEventHandler implements JSBridgeHandler{
    @Override
    public String getName() {
        return "dBounds";
    }

    @JavascriptInterface
    public void onChange(int width,int height) {
        onResize(width,height);
    }

    @Override
    public String getJSCode() {
        String code = "(function() {\n" +
                "\tvar rect = document.body.getBoundingClientRect();\n" +
                "\tdBounds.onChange(rect.width,rect.height);\n" +
                "})();";

        return code;
    }

    public abstract void onResize(int width, int height);
}
