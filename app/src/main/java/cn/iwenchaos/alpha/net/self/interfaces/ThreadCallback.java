package cn.iwenchaos.alpha.net.self.interfaces;

/**
 * Created by chaos
 * on 2019/3/9. 17:16
 * 文件描述：一个回调接口，用于通知用户任务的状态回调委托类
 *
 * 在这个类中，可以看到三种状态[这个是在自定义Callable中的call方法中实现]，
 * 并且在异常时可以打印异常日志，在开始和完成时可以打印线程名称
 */
public interface ThreadCallback {


    /**
     * 当线程发生错误时，将调用此方法。
     * @param threadName            正在运行线程的名字
     * @param t                     异常
     */
    void onError(String threadName, Throwable t);

    /**
     * 通知用户知道它已经完成
     * @param threadName            正在运行线程的名字
     */
    void onCompleted(String threadName);

    /**
     * 通知用户任务开始运行
     * @param threadName            正在运行线程的名字
     */
    void onStart(String threadName);
}
