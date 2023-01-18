package os_ex_09.semaphore;

/** A Semaphore.

    This interface is intended as abstraction of the concept of a
    os_ex_08.semaphore, so that other code can ignore the differences between
    actual implementations.
*/
public interface Semaphore {

    /** Acquire a permit from this os_ex_08.semaphore, blocking if neccessary.
     */
    void acquire() throws InterruptedException;

    /** Release a permit to this os_ex_08.semaphore, possibly unblocking blocked
	threads.
    */
    void release();
}
