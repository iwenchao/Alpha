package cn.iwenchaos.alpha.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.LruCache;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by chaos
 * on 2019/2/22. 15:13
 * 文件描述：
 */
public class ImageLoader {

    private static volatile ImageLoader INSTANCE = null;
    private LruCache<String, Bitmap> mLruCache = null;
    private ExecutorService mExecutorService;
    private android.os.Handler mHandler = new android.os.Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            final Callback callback = (Callback) msg.obj;
            postResult(callback, null);
            return false;
        }
    });

    public static ImageLoader get() {
        if (INSTANCE == null) {
            synchronized (ImageLoader.class) {
                INSTANCE = new ImageLoader();
            }
        }
        return INSTANCE;
    }

    private ImageLoader() {
        int maxSize = (int) Runtime.getRuntime().maxMemory() / 4;
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf(key, value);
            }
        };
        mExecutorService = Executors.newFixedThreadPool(7, new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread("ImageLoader:" + (count++));
            }
        });
    }


    public void load(String url, Callback callback) {
        final String key = url.replace("[\\W]", "");
        Bitmap fromCache = readFromCache(key);
        if (fromCache != null) {
            callback.onResourceReady(fromCache);
            return;
        }

        Bitmap bitmap = readFromFile(key);
        if (bitmap != null) {
            callback.onResourceReady(bitmap);
            return;
        }
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL u = new URL(url);
                    InputStream inputStream = u.openStream();
                    Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);//获取bitmap对象
                    saveToSDCard(key, bitmap1);//保存文件到SD卡
                    saveToCache(key, bitmap1);//保存文件到内存中
                    //使用Handler对象给主线程发送消息
                    Message msg = mHandler.obtainMessage();
                    msg.obj = bitmap1;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void saveToCache(String key, Bitmap bitmap1) {

    }

    private void saveToSDCard(String key, Bitmap bitmap1) {

    }

    private Bitmap readFromFile(String key) {
        return null;
    }

    private Bitmap readFromCache(String key) {
        return null;
    }


    private void postResult(Callback callback, Bitmap bitmap) {
        callback.onResourceReady(bitmap);
    }

    public interface Callback {
        void onResourceReady(Bitmap bitmap);
    }


}
