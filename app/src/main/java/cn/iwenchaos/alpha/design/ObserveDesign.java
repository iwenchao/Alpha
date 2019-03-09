package cn.iwenchaos.alpha.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaos
 * on 2019/3/9. 15:01
 * 文件描述：观察者模式
 * <p>
 * 1. 一个目标物件管理所有相依于它的观察者物件，并且在它本身的状态改变时主动发出通知。
 * 2. 此种模式通常被用来实现事件处理系统。观察者设计模式定义了对象间的一种一对多的依赖关系，
 * 以便一个对象的状态发生变化时，所有依赖于它的对象都得到通知并自动刷新。
 * <p>
 * 3. 观察者Observer：
 * 将自己注册到被观察对象（Subject）中，被观察对象将观察者存放在一个容器（Container）里。
 * 4. 被观察对象Subject：
 * 被观察对象发生了某种变化（如图中的SomeChange），从容器中得到所有注册过的观察者，将变化通知观察者。
 */
public class ObserveDesign {


    public static void main(String[] args) {
        Role subject = new Role();
        IObserver observer1 = new ObserverObj1();
        IObserver observer2 = new ObserverObj2();
        subject.add(observer1);
        subject.add(observer2);
        subject.change("update!");
    }


    //定义观察者接口:
    public interface IObserver {
        void update(String newState);
    }

    //具体的观察者:
    static class ObserverObj1 implements IObserver {


        @Override
        public void update(String newState) {
            System.out.println("观察者1的状态为：" + newState);
        }
    }

    static class ObserverObj2 implements IObserver {

        private String observerState;

        @Override
        public void update(String state) {
            observerState = state;
            System.out.println("观察者2的状态为：" + observerState);
        }
    }

    //定义被观察的角色抽象类:
    static abstract class AbsRole {
        private List<IObserver> mIObserverList = new ArrayList<>();

        public void add(IObserver observer) {
            mIObserverList.add(observer);
        }

        public void remove(IObserver observer) {
            mIObserverList.remove(observer);
        }

        public void notifyObserver(String newState) {
            for (IObserver o : mIObserverList) {
                o.update(newState);
            }
        }


    }
    //被观察角色子类:
    static class Role extends AbsRole{

        private String state;

        public String getState() {
            return state;
        }

        public void change(String newstate){
            state = newstate;
            notifyObserver(state);
        }
    }


}
