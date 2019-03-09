package cn.iwenchaos.alpha.design;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chaos
 * on 2019/3/9. 13:19
 * 文件描述：实现生产者消费者模式的几种方式
 * <p>
 * <p>
 * <p>
 * 1. 生产者消费者模式就是通过一个容器来解决生产者和消费者的强耦合问题。
 * 2. 生产者和消费者彼此之间不直接通讯，而通过阻塞队列来进行通讯，
 * 所以生产者生产完数据之后不用等待消费者处理，直接扔给阻塞队列;
 * 3. 消费者不找生产者要数据，而是直接从阻塞队列里取，阻塞队列就相当于一个缓冲区，平衡了生产者和消费者的处理能力。
 * 4. 这个阻塞队列就是用来给生产者和消费者解耦的。
 */
public class ProductConsumerDesign {


    //
    public static void main(String[] args) {
        List<Product> listProduct=new ArrayList<Product>();
        Lock lock = new ReentrantLock();
        Condition conditionProducer = lock.newCondition();
        Condition conditionConsumer = lock.newCondition();

        Warehouse warehouse = new Warehouse(listProduct,lock, conditionProducer,conditionConsumer);
        Producter producer = new Producter(warehouse);
        Consumer consumer = new Consumer(warehouse);

        new Thread(producer,"生产者A").start();
//        new Thread(producer,"生产者B").start();
//        new Thread(consumer,"消费者C").start();
        new Thread(consumer,"消费者D").start();
    }


    //产品
    static class Product {
        private UUID id;

        public Product(UUID id) {
            this.id = id;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }
    }

    static class Warehouse {
        private final int MAX_SIZE = 5;
        private List<Product> listProduct;
        private Lock lock;
        private Condition conditionProductor;
        private Condition conditionConsumer;

        //
        private BlockingQueue<Product> blockingQueue;

        public Warehouse(List<Product> listProduct, Lock lock, Condition conditionProductor, Condition conditionConsumer) {
            this.listProduct = listProduct;
            this.lock = lock;
            this.conditionProductor = conditionProductor;
            this.conditionConsumer = conditionConsumer;
        }

        public void addProduct() {
            lock.lock();
            try {
                //优先判断 产品库存大小
                String tName = Thread.currentThread().getName();
                while (listProduct.size() >= MAX_SIZE) {
                    System.out.println("产品列表已满，不再生产！" + tName + "进入等待");
                    conditionProductor.await();
                }
                //生产产品
                Product newProduct = new Product(UUID.randomUUID());
                listProduct.add(newProduct);
                System.out.println(tName + "生产了一个产品，它的编号是：" + newProduct.getId().toString());
                //通知消费者
                conditionConsumer.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public Product offerProduct() {
            lock.lock();
            String tName = Thread.currentThread().getName();
            try {
                //优先判断 产品库存大小
                while (listProduct.size() <= 0) {
                    System.out.println("产品列表不足，不再消费！" + tName + "进入等待");
                    conditionConsumer.await();
                }
                //
                Product product = listProduct.get(0);
                System.out.println(tName + "消费了一个产品，它的编号是：" + product.getId().toString());
                listProduct.remove(0);
                conditionProductor.signalAll();
                return product;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            return null;

        }



        public void addProductSynchronized(){
            synchronized (listProduct){

            }
        }
        public void removeProductSynchronized(){
            synchronized (listProduct){

            }
        }


        public void addProductBlockQueue(){
            String tName = Thread.currentThread().getName();
            if (blockingQueue.size()>MAX_SIZE){
                System.out.println("产品列表已满，不再生产！" + tName + "进入等待");
            }else {
                Product product = new Product(UUID.randomUUID());
                System.out.println(tName + "生产了一个产品，它的编号是：" + product.getId().toString());
                try {
                    blockingQueue.put(product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void removeProduct() {
            String currentName = Thread.currentThread().getName();
            if (blockingQueue.size() <= 0) {
                System.out.println("产品列表不足，不再消费！" + currentName + "进入等待");
            } else {
                try {
                    Product product = blockingQueue.take();
                    System.out.println(currentName + "消费了一个产品，它的编号是：" + product.getId().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //生产者
    static class Producter implements Runnable {

        Warehouse mWarehouse;

        public Producter(Warehouse warehouse) {
            mWarehouse = warehouse;

        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mWarehouse.addProduct();
            }

        }
    }
    //消费者
    static class Consumer implements Runnable {

        Warehouse mWarehouse;

        public Consumer(Warehouse warehouse) {
            mWarehouse = warehouse;

        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mWarehouse.offerProduct();
            }

        }
    }

}
