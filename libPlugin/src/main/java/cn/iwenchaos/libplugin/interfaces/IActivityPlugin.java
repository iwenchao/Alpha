package cn.iwenchaos.libplugin.interfaces;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by chaos
 * on 2019/2/23. 16:56
 * 文件描述：
 */
public interface IActivityPlugin {

    void attachContext(FragmentActivity context);

    void onCreate(Bundle saveInstance);

    void onStart();

    void onResume();

    void onRestart();

    void onDestroy();

    void onStop();

    void onPause();

    void onSaveInstanceState(Bundle outState);
}
