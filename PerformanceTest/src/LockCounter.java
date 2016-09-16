import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Kubra on 15.9.2016.
 */
public class LockCounter implements Counter {
    private int value;
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void increment() {
        reentrantLock.lock();
        try {
            value++;
        }
        finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public int get() {
        reentrantLock.lock();
        try {
            return value;
        }
        finally {
            reentrantLock.unlock();
        }
    }
}