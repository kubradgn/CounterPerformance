/**
 * Created by Kubra on 15.9.2016.
 */
public class VolatileCounter implements Counter {
    private volatile int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}

