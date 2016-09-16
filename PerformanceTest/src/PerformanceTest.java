/**
 * Created by Kubra on 15.9.2016.
 */
public class PerformanceTest {
    private static int increments = 1000;
    private static Thread[] threads = new Thread[2];

   public static void main(String[] args) {
       Counter[] counters = {new SynchronizedCounter(), new LockCounter(),
               new AtomicCounter(), new VolatileCounter()};
/*
       Counter[] temp = {new SynchronizedCounter(), new LockCounter(),
               new AtomicCounter(), new VolatileCounter()};

       //This code is for warming up the JVM.
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5000; j++) {
                performOperation(temp[i]);
            }
        }
*/
       printResult("SynchronizedCounter", process(counters[0]));
       printResult("LockCounter        ", process(counters[1]));
       printResult("AtomicCounter      ", process(counters[2]));
       printResult("VolatileCounter    ", process(counters[3]));
    }

    /**
     * Performs operations to be tested.
     */
    private static void performOperation(Counter counter) {
        for (int t = 0; t < 2; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < increments; i++) {
                    counter.increment();
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Cannot join!\nError: " + e);
            }
        }
    }

    /**
     * Returns the elapsed time while performing operation.
     */
    private static long process(Counter counter) {
        long startTime = System.nanoTime();
        performOperation(counter);
        return System.nanoTime() - startTime;
    }

    /**
     * Prints the result for a Counter object.
     */
    private static void printResult(String counterName, long duration) {
        long durationMS = duration / 1000000;
        long durationNS = duration % 1000000;

        System.out.println(counterName + ":   " + durationMS + " ms " + durationNS + " ns");
    }
}

