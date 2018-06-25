package ru.glaizier.codepractice.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author GlaIZier
 */
public class WaitingTaskToFinish {

    public static <T> List<Future<T>> invokeAllWait(ExecutorService executor, List<? extends Callable<T>> tasks)
        throws InterruptedException {
        return executor.invokeAll(tasks);
    }

    public static <T> T getWait(ExecutorService executor, Callable<? extends T> task)
        throws ExecutionException, InterruptedException {
        return executor.submit(task).get();
    }

    public static <T> Future<? extends T> shutdownAwaitWait(ExecutorService executor, Callable<? extends T> task,
                                                            int deadlineInSec)
        throws InterruptedException {
        Future<? extends T> future = executor.submit(task);
        executor.shutdown();
        executor.awaitTermination(deadlineInSec, TimeUnit.SECONDS);
        return future;
    }

    public static <T> List<Future<T>> countDownLatchWait(ExecutorService executor, List<? extends Callable<T>> tasks)
        throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(tasks.size());
        List<Future<T>> futures = new ArrayList<>(tasks.size());
        new Thread(() -> tasks.forEach(task -> {
            Future<T> future = executor.submit(() -> {
                try {
                    System.out.println(futures.size());
                    return task.call();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage(), e);
                } finally {
                    latch.countDown();
                }
            });
            futures.add(future);
        })).start();
        latch.await();
        return futures;
    }

    public static <T> List<Future<T>> completionServiceWait(ExecutorService executor, List<? extends Callable<T>> tasks)
        throws InterruptedException {
        CompletionService<T> completionService = new ExecutorCompletionService<T>(executor);
        tasks.forEach(completionService::submit);
        ArrayList<Future<T>> futures = new ArrayList<>(tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            futures.add(completionService.take());
        }
        return futures;
    }

    public static <T> List<Future<T>> countWait(ExecutorService executor, List<? extends Callable<T>> tasks)
        throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        CopyOnWriteArrayList<Exception> exceptions = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<T> results = new CopyOnWriteArrayList<>();

        ArrayList<Future<T>> futures = new ArrayList<>(tasks.size());
        new Thread(() -> tasks.forEach(task -> {
            Future<T> future = executor.submit(() -> {
                try {
                    T result = task.call();
                    results.add(result);
                    return result;
                } catch (Exception e) {
                    exceptions.add(e);
                    return null;
                } finally {
                    if (results.size() + exceptions.size() == tasks.size()) {
                        lock.countDown();
                    }
                }
            });
            futures.add(future);
        })).start();
        lock.await();
        return futures;
    }

}
