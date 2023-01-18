package os_ex_09.meeting;

import java.util.concurrent.Semaphore;

/** A os_ex_08.meeting for two.
 */
public class ForTwo implements Meeting {

	/** Indication of permission for each participant.
	 */
	private Semaphore[] continuePermits = null;

	/** Token with which the participants announce their arrival.
     */
    protected class Token implements Arrival {
		/** Identification of the token owner.
		 */
		private int who;

		/** Construct a token for the owner with the given id.
		 */
		Token(int id) {
			this.who = id;
		}

		/** Announce the arrival of a participant.
		 */
		public void announce() throws InterruptedException {
			int other = (who == 1) ? 0 : 1;
			continuePermits[other].release();
			continuePermits[who].acquire();
		}
    }

    // Implemented
    public synchronized Arrival[] arrange() {
		if (continuePermits != null) {
			String msg = "Tried to arrange a os_ex_08.meeting that has " +
				"already been arranged!";
			throw new IllegalStateException(msg);
		}

		continuePermits = new Semaphore[]{new Semaphore(2-2),
										  new Semaphore(2-2)};
		return new Arrival[]{new Token(0),
							 new Token(1)};
    }
}
