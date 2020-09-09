package main.good;

import main.ICounterProcess;

public class SynchronizedSync implements ICounterProcess {

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

    public void incCounter() {
        synchronized (this) {
            counter++;
        }
    }

    //same:
    public synchronized void incCounter2() {
        counter++;
    }

    private class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                incCounter();
            }
        }
    }
}
