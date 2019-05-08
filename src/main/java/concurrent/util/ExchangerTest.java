package concurrent.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    //用于线程间数据交换
    private static Exchanger<String> exchanger = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    
    public static void main(String[] args) {
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                String a = "账单a";
                try {
                    exchanger.exchange(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        );
        
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                String b = "账单b";
                try {
                    String a = exchanger.exchange(b);
                    System.out.println("a和b数据是否一致：" + b.equals(a) + ",a数据是：" + a + ",b数据是：" + b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        );
        
        threadPool.shutdown();
        
    }
    
}
