package cn.iwenchaos.alpha.hybrid.web.client;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by chaos
 * on 2019/2/16. 16:14
 * 文件描述：
 */
public class AlWebViewClient extends WebViewClient {


    public AlWebViewClient() {
    }


    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }



    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Nullable
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }



    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }
}
