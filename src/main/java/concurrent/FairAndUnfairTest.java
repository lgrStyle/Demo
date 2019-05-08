package concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {

    @SuppressWarnings("unused")
    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);
    
    public static void main(String[] args) {
//        for(int i = 0; i < 5; i++) {
//            Thread t = new Thread(new Job(fairLock),String.valueOf(i));
//            t.start();
//        }
        
        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(new Job(unfairLock),String.valueOf(i));
            t.start();
        }
    }
    
    
    public static class Job implements Runnable{
        
        private Lock lock;
        public Job(Lock lock) {
            this.lock = lock;
        }
        
        @Override
        public void run() {
            for(int i = 0; i < 2; i++) {
                lock.lock();
                try{
                    System.out.println("Lock by ["+ Thread.currentThread().getName() +"]"+" Waiting by ["+ lock.toString() +"]");
                }finally {
                    lock.unlock();
                }
            }
        }
        
    }
    
    @SuppressWarnings("serial")
    private static class ReentrantLock2 extends ReentrantLock{
        
        public ReentrantLock2(boolean fair){
            super(fair);
        }
        
        public Collection<Thread> getQueuedThreads(){
            List<Thread> list = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(list);
            return list ;
        }
        
        public String toString() {
            Collection<Thread> list = getQueuedThreads();
            String str = "";
            for(Thread t : list) {
                str += t.getName() + " ";
            }
            return str;
        }
    }
}
