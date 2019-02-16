package cn.iwenchaos.alpha;

/**
 * Created by chaos
 * on 2019/2/15. 11:58
 * 文件描述：
 */
public class Alpha {


    public static void main(String[] args){
        String str1 = new String("str")+new String("01");
        str1.intern();
        String str2 = "str01";
        System.out.println(str2==str1);
    }
}
