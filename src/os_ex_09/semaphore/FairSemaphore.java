package os_ex_09.semaphore;

public class FairSemaphore implements Semaphore {

	private final java.util.concurrent.Semaphore semaphore;
	public FairSemaphore(int initial) {
		semaphore = new java.util.concurrent.Semaphore(initial, true);
	}

	public void acquire() throws InterruptedException {
		semaphore.acquire();
	}

	public void release() {
		semaphore.release();
	}


}
