package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomic = new AtomicInteger(0);
        System.out.println(atomic.getAndIncrement());
        System.out.println(atomic.get());
        System.out.println(atomic.compareAndSet(1, 2));
        System.out.println(atomic.get());
    }
}
