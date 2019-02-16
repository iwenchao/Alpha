package cn.iwenchaos.alpha.hybrid.web.client;

import android.graphics.Bitmap;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;

/**
 * Created by chaos
 * on 2019/2/16. 16:13
 * 文件描述：
 */
public class AlWebChromeClient extends WebChromeClient {


    public AlWebChromeClient() {
    }

    //页面加载进度
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    //页面 title
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    //页面 标题icon
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    //页面内触发jsAlert函数
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    //页面内触发jsConfirm函数
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    //页面内触发jsPrompt函数
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    //打印控制台信息
    public boolean onConsoleMessage(ConsoleMessage cm) {
        if (null != cm) {
            Logger.i("AlWebChromeClient", cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
        }
        return super.onConsoleMessage(cm);
    }
}
