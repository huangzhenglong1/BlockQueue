package com.shhxzq.interrup;

/**
 * Created by huangzhenglong on 2017/10/10.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 5000000; i++) {
                if (this.interrupted()) {
                    System.out.println("should be stopped and exit");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }
//            Thread.sleep()、 Thread.join() 或 Object.wait()，那么它将取消阻塞并抛出 InterruptedException
//            Thread.sleep(10000);
            System.out.println(this.isInterrupted());
            System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();

        }
    }
}
