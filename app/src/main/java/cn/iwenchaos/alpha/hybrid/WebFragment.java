package cn.iwenchaos.alpha.hybrid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwenchaos.alpha.R;

/**
 * Created by chaos
 * on 2019/2/16. 15:42
 * 文件描述：
 */
public class WebFragment extends Fragment {

    View mContainerView;

    @BindView(R.id.webview_container)
    FrameLayout mWebviewContainer;

    Unbinder mUnbinder;


    public static WebFragment newInstance() {
        return newInstance(null);
    }

    public static WebFragment newInstance(Bundle args) {
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContainerView == null) {
            mContainerView = inflater.inflate(R.layout.fragment_web, container, false);
        }
        mUnbinder = ButterKnife.bind(this, mContainerView);


        return mContainerView;

    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }
}
