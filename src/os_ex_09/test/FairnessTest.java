package os_ex_09.test;

import os_ex_09.semaphore.Semaphore;
import os_ex_09.semaphore.FairSemaphore;
import os_ex_09.semaphore.UnfairSemaphore;

/** Test of the fairness of a os_ex_08.semaphore.
 */
public class FairnessTest {

	/** Counter that is incremented by the running tasks and used to
	 check whether they're run in correct order.
	 */
	private int counter;

	/** The used os_ex_08.semaphore. This class assumes that it's initial set
	 to have exactly 1 permit.
	 */
	private Semaphore semaphore;

	/** Construct a test instance, using the given os_ex_08.semaphore as locking
	 primitive.
	 */
	public FairnessTest(Semaphore s) {
		this.semaphore = s;
		this.counter = 0;
	}

    /** Task used to check the fairness of a os_ex_08.semaphore.

	This task sleeps an interval to force other tasks to wait
	on the os_ex_08.semaphore, causing multiple threads to wait on the
	os_ex_08.semaphore. At the same time, the task checks if it is allowed
	to enter the critical section at the right time, that is whether
	all tasks enter the critical section in the correct order.
    */
    protected class Task implements Runnable {
		/** Sequential id.
		 */
		private int id;

		/** Construct the task with the sequential id.
		 */
		Task(int i) {
			this.id = i;
		}

		/** Check for correct execution order, indicating fairness
			of the used os_ex_08.semaphore.
		*/
		public void run() {
			try {
				Thread.sleep(id * 100 + 10);
				semaphore.acquire();
				if (counter == id) {
					++counter;
				}
				Thread.sleep(300);
				semaphore.release();
			} catch (InterruptedException ie) {
				counter = -1; // Interrupted, so fail hard
			}
		}
    }

    /** Run the tasks once, checking for their correct execution order.
     */
    public boolean fairRun() {
		counter = 0;
		Thread[] threads = new Thread[13];

		for (int i = 0; i < threads.length; ++i) {
			threads[i] = new Thread(new Task(i));
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException ie) {
				// Unhandled on purpose
			}
		}
		return (counter == threads.length);
    }

    /** Perform the test by repeatedly testing whether the os_ex_08.semaphore could
	allow us to run a {@link #fairRun()}.
    */
    public boolean perform() {
		for (int i = 0; i < 13; ++i) {
			if (! fairRun()) {
				return false;
			}
		}
		return true;
    }

    /** Quick'n'dirty executable check of both the fair and the unfair
	semaphores. Won't compile if you don't have a FairSemaphore.
    */
    public static void main(String[] args) {
		FairnessTest uf = new FairnessTest(new UnfairSemaphore(1));
		System.out.println("UnfairSemaphore seems to be " +
				   (uf.perform() ? "fair" : "unfair"));

		FairnessTest f = new FairnessTest(new FairSemaphore(1));
		System.out.println("FairSemaphore seems to be " +
				   (f.perform() ? "fair" : "unfair"));
    }
}
