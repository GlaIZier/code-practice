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
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
    }

    @Test
    public void completableServiceWait() throws InterruptedException {
        List<Callable<Integer>> tasks = buildTasks();

        List<Future<Integer>> futures = WaitingTaskToFinish.completionServiceWait(executor, tasks);
        IntStream.range(0, THREADS_NUMBER)
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
    }

    @Test
    public void countWait() throws InterruptedException {
        List<Callable<Integer>> tasks = buildTasks();

        List<Future<Integer>> futures = WaitingTaskToFinish.countWait(executor, tasks);
        IntStream.range(0, THREADS_NUMBER)
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
    }

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

    private List<Callable<Integer>> buildTasksWithException() {
        List<Callable<Integer>> tasks = buildTasks();
        Callable<Integer> exceptionTask = () -> {
            throw new RuntimeException("Unexpected exception!");
        };
        tasks.add(THREADS_NUMBER / 2, exceptionTask);
        return tasks;
    }

    @Test(expected = ExecutionException.class)
    public void exceptionInvokeAllWait() throws InterruptedException, ExecutionException {
        List<Future<Integer>> futures = WaitingTaskToFinish.invokeAllWait(executor, buildTasksWithException());

        assertThat(futures.size(), is(THREADS_NUMBER + 1));
        IntStream.range(0, futures.size())
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
        for (Future<Integer> future : futures) {
            future.get();
        }
    }

    @Test(expected = ExecutionException.class)
    public void exceptionGetWait() throws InterruptedException, ExecutionException {
        Callable<Integer> exceptionTask = () -> {
            throw new RuntimeException("Unexpected exception!");
        };
        WaitingTaskToFinish.getWait(executor, exceptionTask);
    }

    @Test(expected = ExecutionException.class)
    public void exceptionShutdownAwaitWait() throws InterruptedException, ExecutionException {
        Future<? extends Integer> future = WaitingTaskToFinish.shutdownAwaitWait(executor, () -> {
            throw new RuntimeException("Unexpected exception");
        }, 1);

        assertThat(future.isDone(), is(true));
        future.get();
    }

    @Test(expected = ExecutionException.class)
    public void exceptionLatchWait() throws InterruptedException, ExecutionException {
        List<Future<Integer>> futures = WaitingTaskToFinish.countDownLatchWait(executor, buildTasksWithException());
        IntStream.range(0, futures.size())
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
        for (Future<Integer> future : futures) {
            future.get();
        }

    }

    @Test(expected = ExecutionException.class)
    public void exceptionCompletableServiceWait() throws InterruptedException, ExecutionException {
        List<Future<Integer>> futures = WaitingTaskToFinish.completionServiceWait(executor, buildTasksWithException());
        IntStream.range(0, futures.size())
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
        for (Future<Integer> future : futures) {
            future.get();
        }
    }

    @Test
    public void exceptionCountWait() throws InterruptedException, ExecutionException {
        List<Future<Integer>> futures = WaitingTaskToFinish.countWait(executor, buildTasksWithException());
        IntStream.range(0, futures.size())
            .forEach(i -> assertThat(futures.get(i).isDone(), is(true)));
        boolean isNull = false;
        for (Future<Integer> future : futures) {
            if (future.get() == null)
                isNull = true;
        }

        assertThat(isNull, is(true));
    }

}
