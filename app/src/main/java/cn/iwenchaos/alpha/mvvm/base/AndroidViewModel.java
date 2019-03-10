package cn.iwenchaos.alpha.mvvm.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chaos
 * on 2019/3/10. 09:57
 * 文件描述：
 */
public class AndroidViewModel extends ViewModel {
    @SuppressLint("StaticFieldLeak")
    private Application mApplication;
    public final CompositeDisposable mDisposable = new CompositeDisposable();

    public AndroidViewModel(@NonNull Application application) {
        mApplication = application;
    }

    /**
     * Return the application.
     */
    @SuppressWarnings("TypeParameterUnusedInFormals")
    @NonNull
    public <T extends Application> T getApplication() {
        //noinspection unchecked
        return (T) mApplication;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
