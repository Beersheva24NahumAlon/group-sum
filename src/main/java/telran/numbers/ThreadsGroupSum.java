package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.FutureTask;

public class ThreadsGroupSum extends GroupSum {
    public ThreadsGroupSum(int[][] groups) {
        super(groups);
    }

    @SuppressWarnings("unchecked")
    @Override
    public long computeSum() {
        FutureTask<Long>[] tasks = new FutureTask[groups.length];
        setupTasks(tasks);
        startTasks(tasks);
        return getSum(tasks);
    }

    private long getSum(FutureTask<Long>[] tasks) {
        return Arrays.stream(tasks).mapToLong(t -> {
            try {
                return t.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

    private void setupTasks(FutureTask<Long>[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
        }
    }

    protected void startTasks(FutureTask<Long>[] tasks) {
        Arrays.stream(tasks).forEach(t -> new Thread(t).start());
    }
}
