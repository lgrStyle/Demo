package concurrent.util;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    
    public static void main(String[] args) throws InterruptedException {
        
        //计算器只能使用一次
        CountDownLatch count = new CountDownLatch(5);
        for(int i = 0; i < 5; i++) {
            new Thread(new Task(count)).start(); 
        }
        
        //计算器为0时唤醒
        count.await();
        System.out.println(Thread.currentThread().getName());
    }

    static class Task implements Runnable{
        private CountDownLatch count ;
        Task(CountDownLatch count ){
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            count.countDown();
        }
        
    }
}
