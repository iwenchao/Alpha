package cn.iwenchaos.alpha.hybrid.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwenchaos.alpha.R;
import cn.iwenchaos.alpha.hybrid.web.client.AlWebChromeClient;
import cn.iwenchaos.alpha.hybrid.web.client.AlWebViewClient;

/**
 * Created by chaos
 * on 2019/2/16. 15:58
 * 文件描述：
 */
public class WrapperWebView extends FrameLayout {
    @BindView(R.id.btn_back)
    ImageView mBtnBack;
    @BindView(R.id.btn_back_line)
    View mBtnBackLine;
    @BindView(R.id.btn_close)
    ImageView mBtnClose;
    @BindView(R.id.btn_action_rlt)
    ImageView mBtnActionRlt;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_c_title)
    TextView mTvCTitle;
    @BindView(R.id.rl_top_navigation_bar)
    RelativeLayout mRlTopNavigationBar;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.fl_webview_container)
    FrameLayout mFlWebviewContainer;
    private WebView mWebView;


    public WrapperWebView(Context context) {
        this(context, null);
    }

    public WrapperWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapperWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initWrapperWebView(context);
        initWebViewSettings();
        initWebViewClient();
    }


    private void initWrapperWebView(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_wrapper_webview, this, true);
        mWebView = new WebView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(layoutParams);
        FrameLayout webviewContainer = (FrameLayout) contentView.findViewById(R.id.fl_webview_container);
        webviewContainer.addView(mWebView, 0);
        ButterKnife.bind(this, contentView);


    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings settings = mWebView.getSettings();
        // // 设置文字解码格式
        settings.setDefaultTextEncodingName("utf-8");
        // 设置与Js交互的权限
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);//开启DOM树缓存
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        //
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        settings.setSaveFormData(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager.getInstance().setAcceptThirdPartyCookies(mWebView, true);
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }
    }


    private void initWebViewClient() {
        mWebView.setWebViewClient(new AlWebViewClient());
        mWebView.setWebChromeClient(new AlWebChromeClient());
    }


    //加载url指定页面
    public void loadUrl(String url) {
        if (!TextUtils.isEmpty(url) && mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    //在Android里通过WebView设置调用JS代码,两种方式
    public void injectJavascript(String js) {
        if (mWebView != null) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    //因为该方法的执行不会使页面刷新，而方法（loadUrl ）的执行则会。Android 4.4 后才可使用
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        mWebView.evaluateJavascript(js, value -> {
                            ////此处为 js 返回的结果
                        });
                    } else {
                        mWebView.loadUrl("javascript:" + js);
                    }
                }
            });
        }
    }

    /**
     * 进行对象映射
     * @param o
     * @param s
     */
    @SuppressLint("AddJavascriptInterface")
    public void addJavascriptInterface(Object o, String s){
        if(mWebView != null){
            mWebView.addJavascriptInterface(o,s);
        }
    }




}
