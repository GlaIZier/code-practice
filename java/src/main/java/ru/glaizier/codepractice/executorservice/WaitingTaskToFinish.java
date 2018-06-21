package ru.glaizier.codepractice.executorservice;

import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

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

    public static <T> Future<? extends T> shutdownAwaitWait(ExecutorService executor, Callable<? extends T> task, int deadlineInSec)
        throws InterruptedException {
        Future<? extends T> future = executor.submit(task);
        executor.shutdown();
        executor.awaitTermination(deadlineInSec, TimeUnit.SECONDS);
        return future;
    }


}
