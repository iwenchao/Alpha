package cn.iwenchaos.alpha.design;

/**
 * Created by chaos
 * on 2019/3/9. 15:17
 * 文件描述：工厂模式
 *
 * 1. 消费者不关心它所要创建对象的类(产品类)的时候。不关心如何创建的时候
 *
 *
 */
public class FactoryDesign {





    ///简单工厂模式
    static class SimpleNoodlesFactory {
        public static final int TYPE_LZ = 1;//兰州拉面
        public static final int TYPE_PM = 2;//泡面
        public static final int TYPE_GK = 3;//干扣面

        public static Object createNoodles(int type) {
            switch (type) {
                case TYPE_LZ:
                    return null;
                case TYPE_PM:
                    return null;
                case TYPE_GK:
                default:
                    return null;
            }
        }
    }
    //工厂方法模式
    ///提供一个用于创建对象的接口(工厂接口)，
    // 让其实现类(工厂实现类)决定实例化哪一个类(产品类)，
    // 并且由该实现类创建对应类的实例。
    /**
     * 1. 可以一定程度上解耦，消费者和产品实现类隔离开
     * 2. 可以一定程度增加扩展性，若增加一个产品实现，只需要实现产品接口，
     * 修改工厂创建产品的方法，消费者可以无感知（
     * 3. 可以一定程度增加代码的封装性、可读性。清楚的代码结构
     *
     * 4. 实现
     *      1. 提供一个产品类的接口
     *      2. 提供一个工厂类的接口
     *      3. 由工厂实现类创建产品类的实例。工厂实现类应有一个方法，用来实例化产品类。
     */



    //抽象工厂模式
    //为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
    /**
     *抽象工厂模式与工厂方法模式的区别
     * 1. 工厂方法模式针对的是一个产品等级结构；而抽象工厂模式则是针对的多个产品等级结构。
     *
     */


}
