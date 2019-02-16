package cn.iwenchaos.alpha.hybrid.web.jsbridge;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.iwenchaos.alpha.hybrid.web.WrapperWebView;
import cn.iwenchaos.alpha.hybrid.web.constant.CallbackType;
import cn.iwenchaos.alpha.hybrid.web.constant.Constant;

/**
 * Created by chaos
 * on 2019/2/16. 16:41
 * 文件描述：JS通过WebView与android原生交互
 */
public final class JsBridge {

    /**
     * jsPlugin容器，key：js调用的方法名 value：相应的jsPlugin
     */
    private Map<String, IJsBridge> jsPluginMap;
    private WrapperWebView mWebView;

    public JsBridge(WrapperWebView wrapperWebView) {
        mWebView = wrapperWebView;
        mWebView.addJavascriptInterface(this, Constant.ALPHA_HYBRID);
    }


    public JsBridge register(String jsFuncName, IJsBridge jsPlugin){
        if (jsPluginMap == null) {
            jsPluginMap = new LinkedHashMap<>();
        }
        if (!TextUtils.isEmpty(jsFuncName) && jsPlugin != null) {
            jsPluginMap.put(jsFuncName, jsPlugin);
        } else {
            throw new UnsupportedOperationException("jsFunction or jsPlugin is not allowed to be empty.");
        }
        return this;
    }



    /**
     * 这个方法统一处理js的调用,且是异步
     *
     * @param funcName
     * @param callbackId
     * @param params
     */
    @JavascriptInterface
    public void jsCallInterfaceAsync(String funcName, final String callbackId, String params) {
        dispatchJSRequest(funcName, callbackId, params);
    }



    /**
     * 分发js请求，异步
     *
     * @param functionName
     * @param callbackId
     * @param params
     */
    private void dispatchJSRequest(final String functionName, final String callbackId, final String params) {
        //run in ui-thread
        IJsBridge jsPlugin = jsPluginMap.get(functionName);
        try {
            if (jsPlugin != null) {
                jsPlugin.setCallbackId(callbackId);
                jsPlugin.setRequestParams(params);
                jsPlugin.setJsBridge(JsBridge.this);
                jsPlugin.jsCallNative(callbackId, params);
            } else {
                nativeCallJS(callbackId, CallbackType.FAIL, params);
            }
        } catch (Exception e) {
            nativeCallJS(callbackId, CallbackType.FAIL, params);
        }

    }


    /**
     * android call js ：android调用js统一调用此方法
     *
     * @param callbackId
     * @param callbackType
     * @param params
     */
    public void nativeCallJS(final String callbackId, final CallbackType callbackType, final String params) {
        if (null == mWebView) {
            return;
        }

        final StringBuilder jsSB = new StringBuilder();
        jsSB.append(Constant.ALPHA_HYBRID + ".callBackFromNative('");
        jsSB.append(callbackId);
        jsSB.append("','");
        jsSB.append(callbackType.getValue());
        jsSB.append(TextUtils.isEmpty(params) ? "')" : ("','" + params + "')"));

        //run in ui-thread
        mWebView.injectJavascript(jsSB.toString());
    }
}
