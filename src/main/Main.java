package main;

import main.bad.BadVolatileSync;
import main.bad.NoSync;
import main.good.AtomicSync;
import main.good.SynchronizedSync;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<ICounterProcess> processes = new ArrayList<>();

        // Bad:
        processes.add(new NoSync());
        processes.add(new BadVolatileSync());

        // Good:
        processes.add(new SynchronizedSync());
        processes.add(new AtomicSync());

        processes.forEach(process -> {
            process.startCounter();
            new WaitThread(process).start();
        });
    }

    // make sure the threads are done incrementing
    private static class WaitThread extends Thread {

        private final ICounterProcess process;

        public WaitThread(ICounterProcess process) {
            this.process = process;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1500);
                System.out.println(process.getClass().getName() + ": " + process.getCounter());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
