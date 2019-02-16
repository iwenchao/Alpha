package cn.iwenchaos.alpha.hybrid.web.jsbridge;

/**
 * Created by chaos
 * on 2019/2/16. 16:42
 * 文件描述：
 */
public interface IJsBridge {



    void setJsBridge(JsBridge jsBridge);

    void reportSuccess(String callbackId);

    void reportSuccessWithParams(String callbackId, String params);

    void reportFail(String callbackId);

    void reportFailWithParams(String callbackId, String params);


    void reportCancel(String callbackId);

    void reportCancelWithParams(String callbackId, String params);

    void reportCompletion(String callbackId);


    /**
     * 所有子类在此实现js调native业务逻辑，异步
     *
     * @param callbackId
     * @param requestParams
     */
    void jsCallNative(String callbackId, String requestParams);

    void setCallbackId(String callbackId);

    void setRequestParams(String params);
}
