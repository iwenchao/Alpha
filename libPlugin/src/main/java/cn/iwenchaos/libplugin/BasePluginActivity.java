package cn.iwenchaos.libplugin;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import cn.iwenchaos.libplugin.interfaces.IActivityPlugin;

/**
 * Created by chaos
 * on 2019/2/23. 17:14
 * 文件描述：
 */
public class BasePluginActivity extends FragmentActivity implements IActivityPlugin {
    //注意：这里命名为protected，以便于子类使用
    protected FragmentActivity thisContext;

    @Override
    public void attachContext(FragmentActivity context) {
        thisContext = context;
    }

    @Override
    public Window getWindow() {
        return thisContext.getWindow();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(thisContext == null){
            super.onCreate(savedInstanceState);
        }else {
            ((IActivityPlugin)thisContext).onCreate(savedInstanceState);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        thisContext.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        thisContext.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        thisContext.setContentView(view, params);
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return thisContext.getLayoutInflater();
    }


    @Override
    public View findViewById(int id) {
        return thisContext.findViewById(id);
    }

    @Override
    public ClassLoader getClassLoader() {
        return thisContext.getClassLoader();
    }

    @Override
    public WindowManager getWindowManager() {
        return thisContext.getWindowManager();
    }


    @Override
    public ApplicationInfo getApplicationInfo() {
        return thisContext.getApplicationInfo();
    }

    @Override
    public void finish() {
        thisContext.finish();
    }


    public void onStart() {
        if(thisContext == null){
            super.onStart();
        }else {
            ((IActivityPlugin)thisContext).onStart();
        }
    }

    public void onResume() {
        if(thisContext == null){
            super.onResume();
        }else {
            ((IActivityPlugin)thisContext).onResume();
        }
    }

    @Override
    public void onRestart() {
        if(thisContext == null){
            super.onRestart();
        }else {
            ((IActivityPlugin)thisContext).onRestart();
        }
    }

    public void onPause() {
        if(thisContext == null){
            super.onPause();
        }else {
            ((IActivityPlugin)thisContext).onPause();
        }
    }

    public void onStop() {
        if(thisContext == null){
            super.onStop();
        }else {
            ((IActivityPlugin)thisContext).onStop();
        }
    }

    public void onDestroy() {
        if(thisContext == null){
            super.onDestroy();
        }else {
            ((IActivityPlugin)thisContext).onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if(thisContext == null){
            super.onSaveInstanceState(outState);
        }else {
            ((IActivityPlugin)thisContext).onSaveInstanceState(outState);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void onBackPressed() {
        thisContext.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
        thisContext.startActivity(intent);
    }

}
