package bandeau;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBandeau extends Bandeau {
    private final ReentrantLock lock = new ReentrantLock();

    public void playScenario(Scenario scenario) {
        lock.lock();
        try {
            scenario.playOn(this);
        } finally {
            lock.unlock();
        }
    }

    public boolean isLocked() {
        return lock.isLocked();
    }
}