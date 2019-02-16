package cn.iwenchaos.alpha.hybrid.web.client;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Set;

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

        // 根据协议的参数，判断是否是所需要的url(原理同方式2)
        // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
        //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

        Uri uri = Uri.parse(message);
        // 如果url的协议 = 预先约定的 js 协议
        // 就解析往下解析参数
        if ( uri.getScheme().equals("js")) {

            // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
            // 所以拦截url,下面JS开始调用Android需要的方法
            if (uri.getAuthority().equals("webview")) {

                //
                // 执行JS所需要调用的逻辑
                System.out.println("js调用了Android的方法");
                // 可以在协议上带有参数并传递到Android上
                HashMap<String, String> params = new HashMap<>();
                Set<String> collection = uri.getQueryParameterNames();

                //参数result:代表消息框的返回值(输入值)
                result.confirm("js调用了Android的方法成功啦");
            }
            return true;
        }
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
