package concurrent.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    //屏障拦截线程数，可以重置，能处理更复杂的业务
    static CyclicBarrier barrier = new CyclicBarrier(3, () -> {System.out.println("barrier task");}) ;
    
    public static void main(String[] args) {
        
        for(int i = 0; i < 2; i++) {
            new Thread(new Task(barrier)).start();
        }
        
        try {
            
            //屏障拦截线程数+1
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        
    }
    
    public static class Task implements Runnable{
        private  CyclicBarrier barrier;
        
        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
        
    }
    
}
