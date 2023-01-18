package os_ex_09.prodCon;

public class ConsumerFactory {

    private ConsumerFactory(){}

    public static Thread create(String name, Strategy buffer, int repeat){
        return new Thread(new Consumer(repeat, buffer), name);
    }

    public static Thread[] createN(String name, Strategy buffer, int repeat, int n){
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++){
            threads[i] = create(name, buffer, repeat);
        }

        return threads;
    }

    private static class Consumer implements Runnable {

        private int repeat;
        private Strategy buffer;

        public Consumer(int repeat, Strategy buffer) {
            this.repeat = repeat;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            for (int i = 0; i < repeat; ++i) {
                String data = null;
                try {
                    data = buffer.extract();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " consumed 1 " + data);
            }
        }
    }
}
