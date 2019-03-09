package cn.iwenchaos.libplugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by chaos
 * on 2019/2/23. 16:56
 * 文件描述：宿主需要一套工具，这个工具用来管理加载Plugin，以及获取Plugin中资源文件等，定义为：PluginManager。
 */
public final class PluginManager {

    private static final PluginManager sPluginManager = new PluginManager();
    private Context mContext;

    private DexClassLoader mPluginDexClassLoader;
    private Resources mPluginResources;

    private PackageInfo mPluginPackageInfo;


    private PluginManager() {
    }

    public static PluginManager get() {
        return sPluginManager;
    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }


    public void loadApk(String dexPath) {
        //dex class loader
        mPluginDexClassLoader = new DexClassLoader(dexPath,
                mContext.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(),
                null,
                mContext.getClassLoader());
        mPluginPackageInfo = mContext.getPackageManager().getPackageArchiveInfo(dexPath,PackageManager.GET_ACTIVITIES);

        //resources 根据AssetManager
        AssetManager assets = null;
        try {
            assets = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assets,dexPath);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        mPluginResources = new Resources(assets,
                mContext.getResources().getDisplayMetrics(),
                mContext.getResources().getConfiguration());
    }


    public DexClassLoader getPluginDexClassLoader() {
        return mPluginDexClassLoader;
    }

    public Resources getPluginResources() {
        return mPluginResources;
    }

    public PackageInfo getPluginPackageInfo() {
        return mPluginPackageInfo;
    }
}
