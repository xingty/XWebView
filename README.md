# About

XWwebView对原生的WebView做了一些简单的扩展，使的Document与Native之间的通信更加方便，让不懂JavaScript的Android开发人员能够方便调用一些Document的接口。

# How To Use

XWwebView 使用非常简单，首先我们要设置一个WebViewClient，在这里使用XWebViewClient替换原生的WebViewClient。

```Java
webView.addEventHandler(handler);
```

# JSBridgeHandler

JSBridgeHandler 用于Native和Document交互，XWebView内置了一些常用的JSBridgeHandler，方便开发者调用。

**ImageEventHandler**

实现点击图片回传地址到Native

```java
final ImageEventHandler handler = new ImageEventHandler(this) {
	@Override
	public void onImageClick(List<String> urls, String url, int index) {
			Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
	}
};
webView.addEventHandler(handler);
```
**DocumentBoundsEventHandler**

获取Document高度回传给Navive

```Java
webView.addEventHandler(new DocumentBoundsEventHandler() {
	@Override
	public void onResize(int width, int height) {
			Toast.makeText(getApplicationContext(),"width:" + width + "  height:" + height,Toast.LENGTH_LONG).show();
	}
});
```

# 自定义JSBridgeHandler

你只需要实现JSBridgeHandler接口，然后编写自己的js代码即可。如果代码量较大，推荐放到assets/js目录，使用AssetUtils工具类加载js文件。