package cn.iwenchaos.alpha.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

import cn.iwenchaos.alpha.BR;

/**
 * Created by chaos
 * on 2019/3/10. 09:12
 * 文件描述：
 */
public class User  extends BaseObservable implements Serializable {

    @Bindable
    String id;
    @Bindable
    String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
}
