package cn.iwenchaos.alpha.net;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import cn.iwenchaos.alpha.R;
import cn.iwenchaos.alpha.net.retrofit.Api;
import cn.iwenchaos.alpha.router.ARouterPath;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by chaos
 * on 2019/2/1. 10:51
 * 文件描述：
 */

@Route(path = ARouterPath.NET_ENTRY_ACTIVITY)
public class EntryNetActivity extends AppCompatActivity {
    public static final String TAG = EntryNetActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_entry);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getAsyncHttpContent();
//                getHttpContent();
                useRetrofitApi();
            }
        });

    }


    /**
     * 使用retrofit2
     */

    private void useRetrofitApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .build();
        Api api = retrofit.create(Api.class);
        retrofit2.Call<ResponseBody> call = api.getWanandroidChapters();
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
            if(response.isSuccessful()){
                Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_LONG).show();
                Logger.d(response.body().string());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void useFinalOkhttp(){
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
