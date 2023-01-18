package os_ex_09.prodCon;

public class ProducerFactory {

    private ProducerFactory(){}

    public static Thread create(String name, Strategy buffer, String data, int repeat){
        return new Thread(new Producer(buffer, data, repeat), name);
    }

    public static Thread[] createN(String name, Strategy buffer, String data, int repeat, int n){
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++){
            threads[i] = create(name, buffer, data, repeat);
        }

        return threads;
    }

    private static class Producer implements Runnable {

        private Strategy buffer;
        private String data;
        private int repeat;

        public Producer(Strategy buffer, String data, int repeat) {
            this.buffer = buffer;
            this.data = data;
            this.repeat = repeat;
        }

        @Override
        public void run() {
            for (int i = 0; i < repeat; ++i) {
                try {
                    buffer.put(data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
