package os_ex_09.prodCon;

public class Buffer implements Strategy {
    private String content;

    public Buffer() {
        this.content = null;
    }

    @Override
    public synchronized void put(String data) throws InterruptedException {
        while (content != null) {
            this.wait();
        }
        this.content = data;
        this.notifyAll();
    }

    @Override
    public synchronized String extract() throws InterruptedException {
        while (content == null) {
            this.wait();
        }
        String data = content;
        content = null;
        this.notifyAll();
        return data;
    }
}
