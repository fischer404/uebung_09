package os_ex_09.meeting;

/** A os_ex_08.meeting, which can be arranged.

    This interface is intended as abstraction over the general concept
    of a os_ex_08.meeting of multiple threads.
    @see Arrival
*/
public interface Meeting {
    /** Arrange the os_ex_08.meeting, getting objects with which one can
	announce ones arrival.

	@return An array of {@link Arrival} instances, each used to
	announce the arrival of one participant.
    */
    Arrival[] arrange();
}
