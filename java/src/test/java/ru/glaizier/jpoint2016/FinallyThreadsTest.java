package ru.glaizier.jpoint2016;

import org.junit.Assert;
import org.junit.Test;

public class FinallyThreadsTest extends Assert {

    /**
     * InterruptException only when thread is sleeping. In other cases: just flag interrupted to true
     * If the code is interrupted finally will be called anyway*
     */
    @Test
    public void test() throws InterruptedException {
        final Boolean[] test = new Boolean[3];
        test[0] = false;
        test[1] = false;
        test[2] = false;
        Thread thread = new Thread(() -> {
            test[0] = true;
            try {
                try {
                    Thread.sleep(500);
                    Thread.currentThread().interrupt();
                } catch (InterruptedException e) {
                    test[1] = true;
                }
            } finally {
                test[2] = true;
            }
        });
        thread.start();
        thread.interrupt();
        Thread.sleep(600);
        thread.interrupt();
        assertTrue(test[0]);
        assertTrue(test[1]);
        assertTrue(test[2]);
    }


}
