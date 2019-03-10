package cn.iwenchaos.alpha.mvvm.net.repo;


import cn.iwenchaos.alpha.mvvm.entity.GirlsData;
import cn.iwenchaos.alpha.mvvm.net.ApiClient;
import io.reactivex.Observable;

/**
 * Created by dxx on 2017/11/8.
 */

public class GankDataRepository {

    public static Observable<GirlsData> getFuliDataRepository(String size, String index){

        Observable<GirlsData> observableForGetFuliDataFromNetWork = ApiClient.getGankDataService().getFuliData(size,index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetFuliDataFromNetWork;
    }



}
