package cn.iwenchaos.alpha;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.iwenchaos.alpha.databinding.ActivityMainBinding;
import cn.iwenchaos.alpha.plugin.ProxyActivity;
import cn.iwenchaos.alpha.router.ARouterPath;
import cn.iwenchaos.libplugin.PluginManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         dataBinding =
                 DataBindingUtil.setContentView(this, R.layout.activity_main);


        findViewById(R.id.entry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouterPath.GirlsListAty).navigation();
            }
        });
    }


    //在宿主中启动PluginMainActivity
    public void loadApk(View view) {
        //注意：使用运行时权限
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PluginManager.get().setContext(this);
        PluginManager.get().loadApk(Environment.getExternalStorageDirectory().getAbsolutePath()+"/otherapk-debug.apk");
    }

    public void startApk(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        String otherApkMainActivityName = PluginManager.get().getPluginPackageInfo().activities[0].name;
        intent.putExtra("className", otherApkMainActivityName);
        startActivity(intent);
    }

}
