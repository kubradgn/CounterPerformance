import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kubra on 15.9.2016.
 */
public class AtomicCounter implements Counter {
    private AtomicInteger value = new AtomicInteger();

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public int get() {
        return value.get();
    }
}
