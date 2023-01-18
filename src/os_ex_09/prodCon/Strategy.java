package os_ex_09.prodCon;

public interface Strategy {

    void put(String data) throws InterruptedException;

    String extract() throws InterruptedException;
}
