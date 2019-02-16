package cn.iwenchaos.alpha.persistence;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chaos
 * on 2019/2/14. 16:11
 * 文件描述：
 */
public class SharePreferenceAlpha extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = this.getSharedPreferences(getLocalClassName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", "value");
        editor.apply();


    }




}
