package jdk;

import org.junit.Test;

public class FinallyThreadsTest {

    /**
     * InterruptException only when thread is sleeping. In other cases: just flag interrupted to true
     * If the code is interrupted finally will be called anyway*
     */
    @Test
    public void test() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("start");
                try {
                    try {
                        Thread.sleep(500);
                        Thread.currentThread().interrupt();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    System.out.println("stop");
                }
            }
        });

        thread.start();
        thread.interrupt();
        Thread.sleep(600);
        thread.interrupt();
    }


}
