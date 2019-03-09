package cn.iwenchaos.alpha.net.self.deliver;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import cn.iwenchaos.alpha.net.self.config.ThreadConfigs;
import cn.iwenchaos.alpha.net.self.interfaces.ThreadCallback;
import cn.iwenchaos.alpha.net.self.task.RunnableWrapper;

/**
 * Created by chaos
 * on 2019/3/9. 17:23
 * 文件描述：
 */
public class PoolThread implements Executor {

    private ThreadLocal local;

    public PoolThread(int type, int size, int priority, String name, ThreadCallback callback, Executor deliver, ExecutorService pool) {

    }

    public static class ThreadBuilder {

        final static int TYPE_CACHE = 0;
        final static int TYPE_FIXED = 1;
        final static int TYPE_SINGLE = 2;
        final static int TYPE_SCHEDULED = 3;

        int type;
        int size;
        int priority = Thread.NORM_PRIORITY;
        String name;
        ThreadCallback callback;
        Executor deliver;
        ExecutorService pool;

        private ThreadBuilder(int size,  int type, ExecutorService pool) {
            this.size = Math.max(1, size);
            this.type = type;
            this.pool = pool;
        }

        /**
         * 通过Executors.newSingleThreadExecutor()创建线程池
         * 内部只有一个核心线程，所有任务进来都要排队按顺序执行
         */
        public static ThreadBuilder create(ExecutorService pool) {
            return new ThreadBuilder(1, TYPE_SINGLE, pool);
        }

        /**
         * 通过Executors.newCachedThreadPool()创建线程池
         * 它是一个数量无限多的线程池，都是非核心线程，适合执行大量耗时小的任务
         */
        public static ThreadBuilder createCacheable() {
            return new ThreadBuilder(0, TYPE_CACHE, null);
        }

        /**
         * 通过Executors.newFixedThreadPool()创建线程池
         * 线程数量固定的线程池，全部为核心线程，响应较快，不用担心线程会被回收。
         */
        public static ThreadBuilder createFixed(int size) {
            return new ThreadBuilder(size, TYPE_FIXED, null);
        }

        /**
         * 通过Executors.newScheduledThreadPool()创建线程池
         * 有数量固定的核心线程，且有数量无限多的非核心线程，适合用于执行定时任务和固定周期的重复任务
         */
        public static ThreadBuilder createScheduled(int size) {
            return new ThreadBuilder(size, TYPE_SCHEDULED, null);
        }

        /**
         * 通过Executors.newSingleThreadPool()创建线程池
         * 内部只有一个核心线程，所有任务进来都要排队按顺序执行
         * 和create区别是size数量
         */
        public static ThreadBuilder createSingle() {
            return new ThreadBuilder(0, TYPE_SINGLE, null);
        }

        /**
         * 将默认线程名设置为“已使用”。
         */
        public ThreadBuilder setName (@NonNull String name) {
            if (name.length()>0) {
                this.name = name;
            }
            return this;
        }

        /**
         * 将默认线程优先级设置为“已使用”。
         */
        public ThreadBuilder setPriority (int priority) {
            this.priority = priority;
            return this;
        }

        /**
         * 将默认线程回调设置为“已使用”。
         */
        public ThreadBuilder setCallback (ThreadCallback callback) {
            this.callback = callback;
            return this;
        }

        /**
         * 设置默认线程交付使用
         */
        public ThreadBuilder setDeliver(Executor deliver) {
            this.deliver = deliver;
            return this;
        }

        /**
         * 创建用于某些配置的线程管理器。
         * @return                  对象
         */
        public PoolThread build () {
            //最大值
            priority = Math.max(Thread.MIN_PRIORITY, priority);
            //最小值
            priority = Math.min(Thread.MAX_PRIORITY, priority);

            size = Math.max(1, size);
            if (name==null || name.length()==0) {
                // 如果没有设置名字，那么就使用下面默认的线程名称
                switch (type) {
                    case TYPE_CACHE:
                        name = "CACHE";
                        break;
                    case TYPE_FIXED:
                        name = "FIXED";
                        break;
                    case TYPE_SINGLE:
                        name = "SINGLE";
                        break;
                    default:
                        name = "POOL_THREAD";
                        break;
                }
            }

//            if (deliver == null) {
//                if (ThreadToolUtils.isAndroid) {
//                    deliver = AndroidDeliver.getInstance();
//                } else {
//                    deliver = JavaDeliver.getInstance();
//                }
//            }
            return new PoolThread(type, size, priority, name, callback, deliver, pool);
        }
    }
    /**
     * 启动任务
     * 这个是实现接口Executor中的execute方法
     * 提交任务无返回值
     * @param runnable              task，注意添加非空注解
     */
    @Override
    public void execute (@NonNull Runnable runnable) {
        //获取线程thread配置信息
        ThreadConfigs configs = getLocalConfigs();
        //设置runnable任务
        runnable = new RunnableWrapper(configs).setRunnable(runnable);
        //启动任务
//        DelayTaskDispatcher.get().postDelay(configs.delay, pool, runnable);
        //重置线程Thread配置
        resetLocalConfigs();
    }

    /**
     * 当启动任务或者发射任务之后需要调用该方法
     * 重置本地配置，置null
     */
    private synchronized void resetLocalConfigs() {
        local.set(null);
    }


    /**
     * 注意需要用synchronized修饰，解决了多线程的安全问题
     * 获取本地配置参数
     * @return
     */
    private synchronized ThreadConfigs getLocalConfigs() {
        ThreadConfigs configs = (ThreadConfigs) local.get();
        if (configs == null) {
            configs = new ThreadConfigs();
//            configs.name = defName;
//            configs.callback = defCallback;
//            configs.deliver = defDeliver;
            local.set(configs);
        }
        return configs;
    }


    /**
     * 为当前的任务设置线程名。
     * @param name              线程名字
     * @return                  PoolThread
     */
    public PoolThread setName(String name) {
        getLocalConfigs().name = name;
        return this;
    }


    /**
     * 设置当前任务的线程回调，如果未设置，则应使用默认回调。
     * @param callback          线程回调
     * @return                  PoolThread
     */
    public PoolThread setCallback (ThreadCallback callback) {
        getLocalConfigs().callback = callback;
        return this;
    }

    /**
     * 设置当前任务的延迟时间.
     * 只有当您的线程池创建时，它才会产生效果。
     * @param time              时长
     * @param unit              time unit
     * @return                  PoolThread
     */
    public PoolThread setDelay (long time, TimeUnit unit) {
        long delay = unit.toMillis(time);
        getLocalConfigs().delay = Math.max(0, delay);
        return this;
    }

    /**
     * 设置当前任务的线程传递。如果未设置，则应使用默认传递。
     * @param deliver           thread deliver
     * @return                  PoolThread
     */
    public PoolThread setDeliver(Executor deliver){
        getLocalConfigs().deliver = deliver;
        return this;
    }
    /**
     * 启动
     * @param delay                     延迟执行的时间，注意默认单位是TimeUnit.MILLISECONDS
     * @param pool                      pool线程池
     * @param task                      runnable
     */
    void postDelay(long delay, final ExecutorService pool, final Runnable task) {
        if (delay == 0) {
            //如果时间是0，那么普通开启
            pool.execute(task);
            return;
        }

//        //延时操作
//        dispatcher.schedule(new Runnable() {
//            @Override
//            public void run() {
//                //在将来的某个时间执行给定的命令。该命令可以在新线程、池线程或调用线程中执行
//                pool.execute(task);
//            }
//        }, delay, TimeUnit.MILLISECONDS);
    }
}