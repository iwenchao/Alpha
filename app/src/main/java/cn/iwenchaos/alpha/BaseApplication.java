package cn.iwenchaos.alpha;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by chaos
 * on 2019/2/1. 11:13
 * 文件描述：
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initRouter();
        initFinalOkHttp();
    }

    private void initFinalOkHttp(){
        OkHttpFinalConfiguration configuration = new OkHttpFinalConfiguration.Builder()
//                .setTimeout()
//                .setCache()
//                .setCacheAge()
//                .setCacheStale()
//                .setCookieJar()
//                .setInterceptors()
//                .setCommenHeaders()
//                .setCommenParams()
                .build();
        OkHttpFinal.getInstance().init(configuration);
    }

    private void initRouter() {
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }


    private void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy("CHAOS") // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("CHAOS")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        FormatStrategy formatStrategy2 = CsvFormatStrategy.newBuilder()
                .tag("custom")
                .build();

        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy2));
    }
}
