package main.bad;

import main.ICounterProcess;

public class NoSync implements ICounterProcess {

    private int counter = 0;

    @Override
    public void startCounter() {
        Thread t1 = new CounterThread();
        Thread t2 = new CounterThread();

        t1.start();
        t2.start();
    }

    @Override
    public int getCounter() {
        return counter;
    }

    private class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        }
    }
}
