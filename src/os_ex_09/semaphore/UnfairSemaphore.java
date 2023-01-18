package os_ex_09.semaphore;

/** Simple, unfair counting os_ex_08.semaphore.

    This class implements a simple and unfair counting os_ex_08.semaphore.
*/
public class UnfairSemaphore implements Semaphore {
    /** Count of available permits.
     */
	private int count;

    /** Object used to communicate with waiting threads.

	A distinct object is used herefore (and not the os_ex_08.semaphore itself)
	to keep any signals from notify() behind the abstraction.
     */
	private final Object wire;

	/**
	 *  Construct a os_ex_08.semaphore with 1 initial permit.
	 */
	public UnfairSemaphore() {
		this(1);
	}

    /**
	 * Construct a os_ex_08.semaphore with the given initial count.
	 * Sets the initial count of the newly constructed os_ex_08.semaphore to the
	 * given value, which may be negative.
    */
    public UnfairSemaphore(int initial) {
		this.count = initial;
		this.wire = new Object();
    }

    // Implementation
    public void acquire() throws InterruptedException {
		synchronized (wire) {
			while (count <= 0) {
				wire.wait();
			}
			--count;
		}
    }

    // Implementation
    public void release() {
		synchronized (wire) {
			++count;
			if (count > 0) {
				wire.notifyAll();
			}
		}
    }
}
