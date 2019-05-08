package concurrent.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    //有10个许可证可用，用前申请许可，用后归还，控制资源的使用
    private static Semaphore semaphore = new Semaphore(10);
    
    private static int TASK_COUNT = 50;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
    
    public static void main(String[] args) {
        for(int i = 0; i < TASK_COUNT; i++) {
            threadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " do something...");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                }
            );
        }
        threadPool.shutdown();
    }
}
