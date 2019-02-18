package cn.iwenchaos.alpha.net;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwenchaos.alpha.R;
import cn.iwenchaos.alpha.bean.MessageEvent;
import cn.iwenchaos.alpha.image.glide.GlideApp;
import cn.iwenchaos.alpha.net.retrofit.Api;
import cn.iwenchaos.alpha.router.ARouterPath;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chaos
 * on 2019/2/1. 10:51
 * 文件描述：
 */

@Route(path = ARouterPath.NET_ENTRY_ACTIVITY)
public class EntryNetActivity extends AppCompatActivity {
    public static final String TAG = EntryNetActivity.class.getSimpleName();
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.my_image_view)
    SimpleDraweeView mMyImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_entry);
        ButterKnife.bind(this);

        View view = findViewById(R.id.btn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getAsyncHttpContent();
//                getHttpContent();
//                useRetrofitApi();

//                useRxjava2();
//                btnClick(view);
//                EventBus.getDefault().post(new MessageEvent());
            }
        });

    }

    private void loadImage(){
        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
        mMyImageView.setImageURI(uri);
    }


    public void btnClick(View view) {
        System.out.println("Hello, I am CSDN_LQR");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

    //    private void useRxjava2Files(){
//        File[] folders = new File[10];
//        Observable.fromArray(folders)
//                .flatMap(new Function<File, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(File file) throws Exception {
//                        return Observable.fromArray(file.listFiles());
//                    }
//                })
//                .filter(new Function<File, Boolean>() {
//                    @Override
//                    public Boolean apply(File file) throws Exception {
//                        return file.getName().endsWith(".png");
//                    }
//                })
//                .map(new Function<File, Bitmap>() {
//                    @Override
//                    public Bitmap apply(File file) throws Exception {
//                        return getBitmapFromFile(file);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Bitmap>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Bitmap r) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    private Bitmap getBitmapFromFile(File file) {
        return null;
    }


    private void useRxjava2() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.d("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d("onNext : " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("onError : " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });

    }


    private void useGlide() {
        String gifUrl = "http://i2.mhimg.com/M00/0E/AE/CgAAilTPWJ2Aa_EIACcMxiZi5xE299.gif";
//        GlideApp.with(this)
////                .load(gifUrl)
////                .errorof(R.drawable.ic_launcher_background)
////                .apply(placeholderOf(R.drawable.ic_launcher_background))
////                .error(errorOf())
////                .into(mImg);
        Target<Drawable> target = GlideApp.with(this)
                .load(gifUrl)
                .placeholder(R.drawable.ic_launcher_background)//暂时站位图
                .error(R.drawable.ic_launcher_background)//永久性错误显示的图片
                .fallback(R.drawable.ic_launcher_background)
                .transform(new MultiTransformation<>(new FitCenter(), new RoundedCorners(3)))
                .transforms(new FitCenter(), new RoundedCorners(3))
                .transition(DrawableTransitionOptions.withCrossFade())//TransitionOptions用于给一个特定的请求指定过渡，而不同的资源类型能决定使用什么类型的过渡选项。Bitmap 和 Drawable可以对应使用使用 BitmapTransitionOptions 或 DrawableTransitionOptions 来指定类型特定的过渡动画。对于 Bitmap 和 Drawable 之外的资源类型，可以使用 GenericTransitionOptions。
                .into(new Target<Drawable>() {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable com.bumptech.glide.request.Request request) {

                    }

                    @Nullable
                    @Override
                    public com.bumptech.glide.request.Request getRequest() {
                        return null;
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }
                });

        GlideApp.with(this).clear(target);

    }


    /**
     * 使用retrofit2
     */

    private void useRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        retrofit2.Call<ResponseBody> call = api.getWanandroidChapters("");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    Logger.d(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * 异步请求,该请求是在主线程
     */
    private void getAsyncHttpContent() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://wanandroid.com/wxarticle/chapters/json")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }

//            @Override
//            public void onFailure(Request request, IOException e) {
//                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String content = response.body().string();
//                Logger.d(content);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }
        });
    }


    /**
     * 同步请求
     */
    private void getHttpContent() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://wanandroid.com/wxarticle/chapters/json")
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_LONG).show();
                Logger.d(response.body().string());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void useFinalOkhttp() {
//        List<File> files = new ArrayList<>();
//        File file = new File("...");
//        RequestParams params = new RequestParams(this);//请求参数
//        params.addFormDataPart("username", mUserName);//表单数据
//        params.addFormDataPart("password", mPassword);//表单数据
//        params.addFormDataPart("file", file);//上传单个文件
//        params.addFormDataPart("files", files);//上传多个文件
//        params.addHeader("token", token);//添加header信息
//        HttpRequest.post(Api.LOGIN, params, new BaseHttpRequestCallback<LoginResponse>() {
//
//            //请求网络前
//            @Override
//            public void onStart() {
//                buildProgressDialog().show();
//            }
//
//            //这里只是网络请求成功了（也就是服务器返回JSON合法）没有没有分装具体的业务成功与失败，大家可以参考demo去分装自己公司业务请求成功与失败
//            @Override
//            protected void onSuccess(LoginResponse loginResponse) {
//                toast(loginResponse.getMsg());
//            }
//
//            //请求失败（服务返回非法JSON、服务器异常、网络异常）
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                toast("网络异常~，请检查你的网络是否连接后再试");
//            }
//
//            //请求网络结束
//            @Override
//            public void onFinish() {
//                dismissProgressDialog();
//            }
    }

}
