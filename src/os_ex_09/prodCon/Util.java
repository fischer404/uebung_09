package os_ex_09.prodCon;

public class Util {
	public static void say(String message) {
		System.out.println(Thread.currentThread().getName() + ": " + message);
	}
}
