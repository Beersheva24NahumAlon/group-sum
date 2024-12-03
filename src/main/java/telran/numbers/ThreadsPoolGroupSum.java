package telran.numbers;

import java.util.concurrent.*;

public class ThreadsPoolGroupSum extends ThreadsGroupSum {

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
            executor.execute(tasks[i]);
        }
        executor.shutdown();
    }

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
    }
}
