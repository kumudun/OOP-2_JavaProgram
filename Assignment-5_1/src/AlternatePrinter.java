public class AlternatePrinter {

    private static class EvenPrinter extends Thread {
        private final int start, end;

        EvenPrinter(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i <= end; i++) {
                if (i % 2 == 0) {
                    System.out.println("Even Thread: " + i);
                }
            }
        }
    }

    private static class OddPrinter extends Thread {
        private final int start, end;

        OddPrinter(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i <= end; i++) {
                if (i % 2 != 0) {
                    System.out.println("Odd Thread: " + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        int start = 1;
        int end = 30;

        Thread even = new EvenPrinter(start, end);
        Thread odd = new OddPrinter(start, end);

        even.start();
        try {
            even.join(); // wait until evens finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        odd.start();
        try {
            odd.join(); // wait until odds finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing complete.");
    }
}
