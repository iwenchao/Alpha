package cn.iwenchaos.alpha.mvvm.girls;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import cn.iwenchaos.alpha.mvvm.entity.GirlsData;
import cn.iwenchaos.alpha.mvvm.net.repo.GankDataRepository;
import cn.iwenchaos.alpha.mvvm.utils.NetUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chaos
 * on 2019/3/10. 09:58
 * 文件描述：
 */
public class GirlsViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }
    //生命周期观察的数据
    private LiveData<GirlsData> mGirlsDataLiveData;
    //ui使用可观察的数据 ObservableField是一个包装类
    private ObservableField<GirlsData> uiObservaleData = new ObservableField<>();


    public GirlsViewModel(@NonNull Application application) {
        super(application);

        mGirlsDataLiveData = Transformations.switchMap(NetUtils.netConnected(application),
                new Function<Boolean, LiveData<GirlsData>>() {
                    @Override
                    public LiveData<GirlsData> apply(Boolean isNetConnected) {
                        Log.i("danxx", "apply------>");
                        if (!isNetConnected) {
                            return ABSENT; //网络未连接返回空
                        }
                        MutableLiveData<GirlsData> applyData = new MutableLiveData<>();

                        GankDataRepository.getFuliDataRepository("20", "1")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<GirlsData>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(GirlsData value) {
                                        Log.i("danxx", "setValue------>");
                                        applyData.setValue(value);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("danxx", "onError------>");
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.i("danxx", "onComplete------>");
                                    }
                                });
                        return applyData;
                    }
                });


    }


    public LiveData<GirlsData> getGirlsDataLiveData() {
        return mGirlsDataLiveData;
    }

    public void setUiObservaleData(GirlsData uiObservaleData) {
        this.uiObservaleData.set(uiObservaleData);
    }
}
