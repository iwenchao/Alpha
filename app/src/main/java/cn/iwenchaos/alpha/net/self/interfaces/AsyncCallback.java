package cn.iwenchaos.alpha.net.self.interfaces;

/**
 * Created by chaos
 * on 2019/3/9. 17:16
 * 文件描述：异步callback回调接口
 *
 * 在这个类中，可以看到三种状态[这个是在自定义Runnable中的run方法中实现]，
 * 并且成功时可以携带结果，在异常时还可以打印异常日志。
 */
public interface AsyncCallback<T> {
    /**
     * 成功时调用
     * @param t         泛型
     */
    void onSuccess(T t);

    /**
     * 异常时调用
     * @param t         异常
     */
    void onFailed(Throwable t);

    /**
     * 通知用户任务开始运行
     * @param threadName            正在运行线程的名字
     */
    void onStart(String threadName);
}
