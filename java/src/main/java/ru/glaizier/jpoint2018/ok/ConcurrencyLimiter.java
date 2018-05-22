package ru.glaizier.jpoint2018.ok;

import java.util.concurrent.Semaphore;

// How to achieve limit change in runtime in a thread-safe way
public class ConcurrencyLimiter {
    static final int DEFAULT_LIMIT = 10;
    final Semaphore semaphore = new Semaphore(DEFAULT_LIMIT);

    // my additional code
    private volatile int totalPermits = DEFAULT_LIMIT;
    private final Object lock = new Object();

    final ConcurrencyLimiter.MySemaphore anotherSemaphore = new ConcurrencyLimiter.MySemaphore(DEFAULT_LIMIT);
    private static class MySemaphore extends Semaphore {
        private MySemaphore(int permits) {
            super(permits);
        }
        private void decreasePermits(int permits) {
            this.reducePermits(permits);
        }
    }
    // end of my code

    // this method restricts the number of possible calls to some heavy task by the limit
    void runTask(Runnable task) {
        semaphore.acquireUninterruptibly();
        try {
            task.run();
        } finally {
            semaphore.release();
        }
    }

    void setLimit(int newLimit) throws InterruptedException {
        assert newLimit > 0;
        // implement me
        if (newLimit == totalPermits) {
            return;
        }
        synchronized (lock) {
            int delta = newLimit - totalPermits;
            if (delta > 0) {
                semaphore.release(delta);
            } else {
                if (!semaphore.tryAcquire(Math.abs(delta))) {
                    throw new RuntimeException("Couldn't decrease permits! Try later...");
                }
            }
            totalPermits = newLimit;
        }
    }

    // my another implementation
    void anotherSetLimit(int newLimit) {
        assert newLimit > 0;
        // implement me
        if (newLimit == totalPermits) {
            return;
        }
        synchronized (lock) {
            int delta = newLimit - totalPermits;
            if (delta > 0) {
                anotherSemaphore.release(delta);
            } else {
                anotherSemaphore.decreasePermits(delta);
            }
            totalPermits = newLimit;
        }
    }

}
