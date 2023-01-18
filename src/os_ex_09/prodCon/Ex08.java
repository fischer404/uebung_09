package os_ex_09.prodCon;

public class Ex08 {

	public static void main(String[] args) throws InterruptedException {
//		Buffer buffer = new Buffer();
		BufferStack buffer = new BufferStack(2);

		/*
		  Creates a Producer-type Runnable that fills the buffer
		  n times (in a for-loop) with the given String, and packs
		  the Runnable into a Thread.
		 */
		Thread producer = ProducerFactory.create("P", buffer, "pizza", 5);
		Thread consumer = ConsumerFactory.create("C", buffer, 5);

		System.out.println("Starting ...");

		producer.start();
		consumer.start();

		System.out.println("All up and running, waiting for finish ...");

		producer.join();
		consumer.join();

		System.out.println("Done, Bye!");
	}
}
