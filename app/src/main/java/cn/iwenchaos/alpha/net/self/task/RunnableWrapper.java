package cn.iwenchaos.alpha.net.self.task;

import java.util.concurrent.Callable;

import cn.iwenchaos.alpha.net.self.config.ThreadConfigs;

/**
 * Created by chaos
 * on 2019/3/9. 17:12
 * 文件描述：
 */
public class RunnableWrapper implements Runnable {

    private String name;
    private Object delegate;
    private Runnable runnable;
    private Callable callable;

    public RunnableWrapper(ThreadConfigs configs) {

    }

    /**
     * 启动异步任务，普通的
     * @param runnable              runnable
     * @return                      对象
     */
    public RunnableWrapper setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    /**
     * 异步任务，回调用于接收可调用任务的结果
     * @param callable              callable
     * @return                      对象
     */
    public RunnableWrapper setCallable(Callable callable) {
        this.callable = callable;
        return this;
    }
    @Override
    public void run() {
        Thread current = Thread.currentThread();
//        ThreadToolUtils.resetThread(current, name, delegate);
//        //开始
//        delegate.onStart(name);
//        //注意需要判断runnable，callable非空
//        // avoid NullPointException
//        if (runnable != null) {
//            runnable.run();
//        } else if (callable != null) {
//            try {
//                Object result = callable.call();
//                //监听成功
//                delegate.onSuccess(result);
//            } catch (Exception e) {
//                //监听异常
//                delegate.onError(name, e);
//            }
//        }
//        //监听完成
//        delegate.onCompleted(name);
    }
}
