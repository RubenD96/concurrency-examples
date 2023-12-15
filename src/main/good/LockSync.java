package main.good;

import main.ICounterProcess;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSync implements ICounterProcess {

    private int counter = 0;
    private final Lock lock = new ReentrantLock();

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
        lock.lock();
        try {
            counter++;
        } finally { // should guarantee no deadlock
            lock.unlock();
        }
    }

    private class CounterThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                incCounter();
            }
        }

        /**
         * This works too, but blocks 1 thread until the other is completely finished counting to 10k
         */
        /*@Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10000; i++) {
                    incCounter();
                }
            } finally {
                lock.unlock();
            }
        }*/
    }
}
