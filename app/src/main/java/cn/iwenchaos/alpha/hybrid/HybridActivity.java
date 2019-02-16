package cn.iwenchaos.alpha.hybrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwenchaos.alpha.R;

/**
 * Created by chaos
 * on 2019/2/16. 15:39
 * 文件描述：
 */
public class HybridActivity extends AppCompatActivity {


    @BindView(R.id.hybrid_container)
    LinearLayout mHybridContainer;


    Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid);
        mUnbinder = ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.hybrid_container, WebFragment.newInstance())
                    .commit();
        }

    }


    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }
}
