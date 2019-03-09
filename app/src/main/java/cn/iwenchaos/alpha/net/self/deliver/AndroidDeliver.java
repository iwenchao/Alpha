package cn.iwenchaos.alpha.net.self.deliver;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by chaos
 * on 2019/3/9. 17:19
 * 文件描述：
 */
public class AndroidDeliver implements Executor {

    private static AndroidDeliver sAndroidDeliver = new AndroidDeliver();
    private Handler mMainHandler = new Handler(Looper.getMainLooper());


    public static AndroidDeliver getAndroidDeliver() {
        return sAndroidDeliver;
    }


    @Override
    public void execute(Runnable command) {
        //返回应用程序的looper，将结果回调到主线程
        Looper mainLooper = Looper.getMainLooper();
        if (Looper.myLooper() == mainLooper) {
            command.run();
            return;
        }

        //开启子线程
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (command != null) {
                    command.run();
                }
            }
        });

    }
}
