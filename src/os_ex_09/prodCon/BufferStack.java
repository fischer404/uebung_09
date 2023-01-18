package os_ex_09.prodCon;

import java.util.LinkedList;

public class BufferStack implements Strategy {
    private String[] content;
    private int length;
    private int last;

    public BufferStack(int length) {
        this.content = new String[length];
        this.length = length;
        this.last = 0;
    }

    private synchronized void enqueue(String data) {
        content[last] = data;
        last++;
    }

    private synchronized String dequeue() {
        String tepm = content[0];
        for (int i = 0; i < length - 1; i++) {
            content[i] = content[i + 1];
        }
        last--;
        return tepm;
    }


    @Override
    public synchronized void put(String data) throws InterruptedException {
        while (last == length) {
            this.wait();
        }
        enqueue(data);
        this.notifyAll();
    }

    @Override
    public synchronized String extract() throws InterruptedException {
        while (last == 0) {
            this.wait();
        }
        String data = dequeue();
        this.notifyAll();
        return data;
    }
}
