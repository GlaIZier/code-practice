package ru.glaizier.jpoint2018.ok;

import java.util.concurrent.Semaphore;

// How to achieve limit change in runtime in a thread-safe way
public class ConcurrencyLimiter {
    static final int DEFAULT_LIMIT = 10;
    final Semaphore semaphore = new Semaphore(DEFAULT_LIMIT);

    // this method restricts the number of possible calls to some heavy task by the limit
    void runTask(Runnable task) {
        semaphore.acquireUninterruptibly();
        try {
            task.run();
        } finally {
            semaphore.release();
        }
    }

    void setLimit(int newLimit) {
        assert newLimit > 0;
        // implement me
    }

}
