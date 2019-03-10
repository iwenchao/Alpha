package cn.iwenchaos.alpha.mvvm.girls;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.iwenchaos.alpha.R;
import cn.iwenchaos.alpha.databinding.ActivityGirlsBinding;
import cn.iwenchaos.alpha.mvvm.base.BaseActivity;
import cn.iwenchaos.alpha.mvvm.entity.GirlsData;
import cn.iwenchaos.alpha.router.ARouterPath;

/**
 * Created by chaos
 * on 2019/3/10. 09:48
 * 文件描述：
 */
@Route(path = ARouterPath.GirlsListAty)
public class GirlActivity extends BaseActivity {

    ActivityGirlsBinding mBinding;
    GirlsAdapter mGirlsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置v 进行binding
        setTitle("呵呵");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_girls);


        //vm
        GirlsViewModel girlsViewModel =
                ViewModelProviders.of(GirlActivity.this).get(GirlsViewModel.class);
        mGirlsAdapter = new GirlsAdapter(new GirlsAdapter.GirlItemClickCallback() {
            @Override
            public void onClick(GirlsData.ResultsBean girlsItem) {
                Toast.makeText(GirlActivity.this, girlsItem.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.setRecyclerAdapter(mGirlsAdapter);
//        mBinding.girlsList.set
        girlsViewModel.getGirlsDataLiveData().observe(this, new Observer<GirlsData>() {
            @Override
            public void onChanged(@Nullable GirlsData girlsData) {
                girlsViewModel.setUiObservaleData(girlsData);
                mGirlsAdapter.setData(girlsData.getResults());
            }
        });
    }
}
