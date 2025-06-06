package org.burgas.recursivefactorial;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public final class FactorialTask extends RecursiveTask<Long> {

    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 0)
            return 1L;

        FactorialTask subtask = new FactorialTask(n - 1);
        ForkJoinTask<Long> fork = subtask.fork();
        try {
            System.out.println(fork.get());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return n * subtask.join();
    }
}
