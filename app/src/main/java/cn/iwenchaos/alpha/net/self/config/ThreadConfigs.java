package cn.iwenchaos.alpha.net.self.config;

import java.util.concurrent.Executor;

import cn.iwenchaos.alpha.net.self.interfaces.AsyncCallback;
import cn.iwenchaos.alpha.net.self.interfaces.ThreadCallback;

/**
 * Created by chaos
 * on 2019/3/9. 17:18
 * 文件描述：
 */
public class ThreadConfigs {


    /**
     * 线程的名称
     * 通过setName方法设置
     */
    public String name;
    /**
     * 线程执行延迟的时间
     * 通过setDelay方法设置
     */
    public long delay;
    /**
     * 线程执行者
     * JAVA或者ANDROID
     */
    public Executor deliver;
    /**
     * 用户任务的状态回调callback
     */
    public ThreadCallback callback;
    /**
     * 异步callback回调callback
     */
    public AsyncCallback asyncCallback;

}
