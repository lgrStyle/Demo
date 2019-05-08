package concurrent;

public class MemoryBarrier {

    private int a = 0;
    private volatile boolean flag = false;
    
    public void write() {
        a = 1;
        flag = true;
    }
    
    public int read() {
        if(flag) {
            return a;
        }else {
            return 0;
        }
    }
}
