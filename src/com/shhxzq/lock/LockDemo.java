package com.shhxzq.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huangzhenglong on 2017/10/10.
 */
public class LockDemo implements Runnable
{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run()
    {
        for (int j = 0; j < 10; j++)
        {
            lock.lock();
            try
            {
                Thread.sleep(100);
                i++;

            }catch (InterruptedException e){

            }
            finally
            {
                lock.unlock();
//                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        LockDemo test = new LockDemo();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
//        Thread.yield();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}