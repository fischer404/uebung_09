package os_ex_09.aufgabe2;

import java.util.concurrent.atomic.AtomicBoolean;

public class spinLock {
    private final AtomicBoolean bool = new AtomicBoolean();

    public void busyWait() {
        while (bool.get()) {

        }
    }

    public void setLock() {
        bool.set(true);
    }

    public void resetLock() {
        bool.set(false);
    }
}
