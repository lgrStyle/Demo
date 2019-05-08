package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskTest extends RecursiveTask<Integer>{

    private static final long serialVersionUID = 2651310792952571518L;

    private static final int THRESHOLD = 5;
    private int start;
    private int end;
    
    public ForkJoinTaskTest(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for(int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            int mid = (end - start) / 2;
            ForkJoinTaskTest leftTask = new ForkJoinTaskTest(start, mid);
            ForkJoinTaskTest rightTask = new ForkJoinTaskTest(mid+1, end);
            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTaskTest task = new ForkJoinTaskTest(0,10);
        Future<Integer> result = forkJoinPool.submit(task);
        System.out.println("result: "+ result.get());
    }
    
}
