package main.good;

import main.ICounterProcess;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSync implements ICounterProcess {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void startCounter() {
        Thread t1 = new CounterThread();
        Thread t2 = new CounterThread();

        t1.start();
        t2.start();
    }

    @Override
    public int getCounter() {
        return counter.get();
    }

    private class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.addAndGet(1);
            }
        }
    }
}
