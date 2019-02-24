package cn.iwenchaos.alpha.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by chaos
 * on 2019/2/23. 10:33
 * 文件描述：
 */
public class User implements Parcelable {

   private int age;
   private String name;
    public ArrayList<String> tags;

    protected User(Parcel in) {
        age = in.readInt();
        name = in.readString();
        tags = in.createStringArrayList();
    }

    //反序列化需要定义一个CREATOR的变量
    public static final Creator<User> CREATOR = new Creator<User>() {
        /**
         * 从序列化后的对象中创建原始对象
         */
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        /**
         * 创建指定长度的原始对象数组
         */
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * 通过上面的描述可以看出,只针对一些特殊的需要描述信息的对象,需要返回1,其他情况返回0就可以
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    //实现序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }
}
