import java.util.Random;

public class ParallelArrayCalculation {

    static class Summer extends Thread {
        private final int[] data;
        private final int from, to; // [from, to)
        private long partial;

        Summer(int[] data, int from, int to) {
            this.data = data;
            this.from = from;
            this.to = to;
        }

        public void run() {
            long s = 0L;
            for (int i = from; i < to; i++) s += data[i];
            partial = s;
        }

        long getPartial() { return partial; }
    }

    public static void main(String[] args) throws InterruptedException {
        final int N = 100_000;
        int[] arr = new int[N];
        Random rnd = new Random();

        for (int i = 0; i < N; i++) arr[i] = rnd.nextInt(100);

        int cores = Runtime.getRuntime().availableProcessors();
        int chunk = (N + cores - 1) / cores;
        int threadsCount = (N + chunk - 1) / chunk;

        Summer[] workers = new Summer[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            int from = i * chunk;
            int to = Math.min(from + chunk, N);
            workers[i] = new Summer(arr, from, to);
            workers[i].start();
        }

        long total = 0L;
        for (int i = 0; i < threadsCount; i++) {
            workers[i].join();
            total += workers[i].getPartial();
        }

        System.out.println(total);
    }
}
