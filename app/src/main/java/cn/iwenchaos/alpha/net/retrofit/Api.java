package cn.iwenchaos.alpha.net.retrofit;

import cn.iwenchaos.alpha.net.bean.ResultVo;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by chaos
 * on 2019/2/2. 17:34
 * 文件描述：
 */
public interface Api {


    /**
     * 添加请求头字段auth
     * @param auth
     * @return
     */
    @Headers("os:android")//作用于方法之上，常用的请求头字段；而Header注解作用于方法参数，特定场景用处
    @GET("wxarticle/chapters/json")
    Call<ResponseBody> getWanandroidChapters(@Header("auth") String auth);

    //或者
    @HTTP(method = "get" , path = "wxarticle/chapters/json" ,hasBody = false)
    Call<ResponseBody> getWanandroidChapters2();

    @GET("wxarticle/chapters/json")
    Observable<ResultVo> getByRxjava2WanandroidChapter3();


    @GET("wxarticle/list/{id}/{page}/json")
    Call<ResponseBody> getSomeoneChapter(@Path("id") String id, @Path("page") int page);


    @POST("/login")
    @FormUrlEncoded
    Observable<ResultVo> doLogin(@Field("user_name")String username,@Field("pass_word")String password);


    @POST("/upload/images/")
    @Multipart
    Observable<ResultVo> doUploadFile(@Part("username") RequestBody username
            , @Part("age")RequestBody age, @Part MultipartBody.Part imageFile);

}
