package cn.iwenchaos.alpha.net.self.task;

import java.util.concurrent.Callable;

/**
 * Created by chaos
 * on 2019/3/9. 17:15
 * 文件描述：
 */
public class CallableWrapper<T> implements Callable<T> {

//
//    private String name;
//    private ThreadCallback callback;
//    private Callable<T> proxy;
//
//    /**
//     * 构造方法
//     * @param configs               thread配置，主要参数有：线程name，延迟time，回调callback，异步callback
//     * @param proxy                 线程优先级
//     */
//    public CallableWrapper(ThreadConfigs configs, Callable<T> proxy) {
//        this.name = configs.name;
//        this.proxy = proxy;
//        this.callback = new CallbackDelegate(configs.callback, configs.deliver, configs.asyncCallback);
//    }
//
    @Override
    public T call() throws Exception {
//        ThreadToolUtils.resetThread(Thread.currentThread(),name,callback);
//        if (callback != null) {
//            //开始
//            callback.onStart(name);
//        }
        T t = null;
//        try {
//            t = proxy == null ? null : proxy.call();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //异常错误
//            if(callback!=null){
//                callback.onError(name,e);
//            }
//        }finally {
//            //完成
//            if (callback != null)  {
//                callback.onCompleted(name);
//            }
//        }
        return t;
    }
}
