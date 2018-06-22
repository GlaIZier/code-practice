package ru.glaizier.codepractice.executorservice;

import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * @author GlaIZier
 */
public class WaitingTaskToFinishTest {

    private static final int THREADS_NUMBER = 100;

    private ExecutorService executor;

    @Before
    public void setUp() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
    }

    @Test
    public void invokeAllWait() throws InterruptedException, ExecutionException {
        List<Callable<Integer>> tasks = buildTasks();

        List<Future<Integer>> futures = WaitingTaskToFinish.invokeAllWait(executor, tasks);
        IntStream.range(0, THREADS_NUMBER)
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
    }

    @Test
    public void getWait() throws InterruptedException, ExecutionException {
        Integer fortyTwo = WaitingTaskToFinish.getWait(executor, () -> {
            Thread.yield();
            Thread.sleep(100);
            Thread.yield();
            return 42;
        });

        assertThat(fortyTwo, is(42));
    }

    @Test
    public void shutdownAwaitWait() throws InterruptedException, ExecutionException {
        Future<? extends Integer> future = WaitingTaskToFinish.shutdownAwaitWait(executor, () -> {
            Thread.yield();
            Thread.sleep(100);
            Thread.yield();
            return 42;
        }, 1);

        assertThat(future.isDone(), is(true));
        assertThat(future.get(), is(42));
    }

    @Test
    public void latchWait() throws InterruptedException {
        List<Callable<Integer>> tasks = buildTasks();

        List<Future<Integer>> futures = WaitingTaskToFinish.countDownLatchWait(executor, tasks);
        IntStream.range(0, THREADS_NUMBER)
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));    }

    private List<Callable<Integer>> buildTasks() {
        return IntStream.range(0, THREADS_NUMBER)
                .mapToObj(i -> (Callable<Integer>) () -> {
                    Thread.yield();
                    Thread.sleep(100);
                    Thread.yield();
                    return i;
                })
                .collect(toList());
    }

}
