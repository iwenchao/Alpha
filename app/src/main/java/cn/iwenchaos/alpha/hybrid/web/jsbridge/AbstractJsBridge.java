package cn.iwenchaos.alpha.hybrid.web.jsbridge;

/**
 * Created by chaos
 * on 2019/2/16. 16:46
 * 文件描述：
 */
public abstract class AbstractJsBridge implements IJsBridge {



    @Override
    public void setCallbackId(String callbackId) {

    }

    @Override
    public void setRequestParams(String params) {

    }


    @Override
    public void setJsBridge(JsBridge jsBridge) {

    }

    @Override
    public void reportSuccess(String callbackId) {

    }

    @Override
    public void reportSuccessWithParams(String callbackId, String params) {

    }

    @Override
    public void reportFail(String callbackId) {

    }

    @Override
    public void reportFailWithParams(String callbackId, String params) {

    }

    @Override
    public void reportCancel(String callbackId) {

    }

    @Override
    public void reportCancelWithParams(String callbackId, String params) {

    }

    @Override
    public void reportCompletion(String callbackId) {

    }



}
