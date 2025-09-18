import java.util.Random;

public class TicketReservationSystem {


    static class Theater {
        private int seats;

        Theater(int seats) {
            this.seats = seats;
        }

        public synchronized boolean reserve(int customerId, int tickets) {
            if (tickets <= seats) {
                seats -= tickets;
                System.out.println("Customer " + customerId + " reserved " + tickets + " tickets.");
                return true;
            } else {
                System.out.println("Customer " + customerId + " couldn't reserve " + tickets + " tickets.");
                return false;
            }
        }
    }


    static class Customer implements Runnable {
        private final Theater theater;
        private final int id;
        private final int requested;

        Customer(Theater theater, int id, int requested) {
            this.theater = theater;
            this.id = id;
            this.requested = requested;
        }

        @Override
        public void run() {
            theater.reserve(id, requested);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int totalSeats = 10;     // theater capacity
        final int customers = 15;      // number of customers
        final int maxRequest = 4;      // each customer may request 1..4 tickets

        Theater theater = new Theater(totalSeats);
        Thread[] threads = new Thread[customers];
        Random rnd = new Random();

        for (int i = 0; i < customers; i++) {
            int request = 1 + rnd.nextInt(maxRequest);
            threads[i] = new Thread(new Customer(theater, i + 1, request));
            threads[i].start();
        }

        for (Thread t : threads) t.join();
    }
}


