package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.*;

public class ThreadsPoolGroupSum extends ThreadsGroupSum {

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Arrays.stream(tasks).forEach(t -> executor.execute(t));
        executor.shutdown();
    }

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
    }
}
