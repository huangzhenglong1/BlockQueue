package com.shhxzq.queue;

import java.util.concurrent.*;

/**
 * Created by huangzhenglong on 2017/10/10.
 */
    public class BlockQueueDemo {
        /**
         *
         * 定义装苹果的篮子
         *
         */
        public class Basket {
            // 篮子，能够容纳3个苹果
            BlockingQueue<String> basket = new ArrayBlockingQueue<String>(10);

            // 生产苹果，放入篮子
            public void produce() throws InterruptedException {
                // put方法放入一个苹果，若basket满了，等到basket有位置
                basket.add("An apple");
            }

            // 消费苹果，从篮子中取走
            public String consume() throws InterruptedException {
                // take方法取出一个苹果，若basket为空，等到basket有苹果为止(获取并移除此队列的头部)
                return basket.remove();
            }
        }

        // 定义苹果生产者
        class Producer implements Runnable {
            private String instance;
            private Basket basket;

            public Producer(String instance, Basket basket) {
                this.instance = instance;
                this.basket = basket;
            }

            public void run() {
                try {
                    while (true) {
                        // 生产苹果
                        System.out.println("生产者准备生产苹果：" + instance);
                        basket.produce();
                        System.out.println("!生产者生产苹果完毕：" + instance);
                        // 休眠300ms
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Producer Interrupted");
                }
            }
        }

        // 定义苹果消费者
        class Consumer implements Runnable {
            private String instance;
            private Basket basket;

            public Consumer(String instance, Basket basket) {
                this.instance = instance;
                this.basket = basket;
            }

            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        System.out.println("消费者准备消费苹果：" + instance);
                        System.out.println(basket.consume());
                        System.out.println("!消费者消费苹果完毕：" + instance);
                        // 休眠1000ms
                        Thread.sleep(10000);
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Consumer Interrupted");
                }
            }
        }

        public static void main(String[] args) {
            BlockQueueDemo test = new BlockQueueDemo();

            // 建立一个装苹果的篮子
            Basket basket = test.new Basket();

            ExecutorService service = Executors.newCachedThreadPool();
            Producer producer = test.new Producer("生产者001", basket);
//            Producer producer2 = test.new Producer("生产者002", basket);
//            Producer producer3 = test.new Producer("生产者003", basket);
//            Producer producer4 = test.new Producer("生产者004", basket);


            Consumer consumer = test.new Consumer("消费者001", basket);
//            Consumer consumer2 = test.new Consumer("消费者002", basket);
//            Consumer consumer3= test.new Consumer("消费者003", basket);
//            Consumer consumer4 = test.new Consumer("消费者004", basket);

            service.submit(producer);
//            service.submit(producer2);
//            service.submit(producer3);
//            service.submit(producer4);
            service.submit(consumer);
//            service.submit(consumer2);
//            service.submit(consumer3);
//            service.submit(consumer4);

            // 程序运行5s后，所有任务停止
//        try {
//            Thread.sleep(1000 * 5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        service.shutdownNow();
        }


}
