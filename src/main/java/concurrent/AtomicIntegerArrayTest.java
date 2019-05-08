package concurrent;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    
    public static void main(String[] args) {
        int[] array = {1,2,3};
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(array);
        System.out.println(atomicArray.getAndAdd(1, 4));
        System.out.println(atomicArray.get(1));
        System.out.println(atomicArray.compareAndSet(2, 3, 8));
        System.out.println(atomicArray.get(2));
    }
    
}
