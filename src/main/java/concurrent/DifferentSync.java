package concurrent;

public class DifferentSync {

    public static synchronized void sync1() {
        System.out.println("sync1");
    }
    
    public synchronized void sync2() {
        System.out.println("sync2");
    }
    
    public void sync3() {
        synchronized(this) {
            System.out.println("sync3");
        }
    }
}
