package cn.iwenchaos.alpha.net.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chaos
 * on 2019/2/2. 17:34
 * 文件描述：
 */
public interface Api {


    @GET("wxarticle/chapters/json")
    Call<ResponseBody> getWanandroidChapters();


}
