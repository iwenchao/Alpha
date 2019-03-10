package cn.iwenchaos.alpha.mvvm.net.service;


import cn.iwenchaos.alpha.mvvm.entity.GirlsData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dxx on 2017/11/8.
 */

public interface GankDataService {

    @GET("api/data/福利/{size}/{index}")
    Observable<GirlsData> getFuliData(@Path("size") String size, @Path("index") String index);


}
