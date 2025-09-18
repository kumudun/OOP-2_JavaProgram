import java.util.ArrayList;
import java.util.List;

// Thread-safe wrapper for ArrayList
class SafeList {
    private final List<String> list = new ArrayList<>();

    public synchronized void add(String element) {
        list.add(element);
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized boolean remove(String element) {
        return list.remove(element);
    }
}

// Worker that performs random operations on SafeList
class Worker implements Runnable {
    private final SafeList safeList;
    private final String name;

    Worker(SafeList safeList, String name) {
        this.safeList = safeList;
        this.name = name;
    }

    @Override
    public void run() {
        // Add element
        safeList.add(name);
        // Query size
        int s = safeList.size();
        System.out.println(name + " added. Current size: " + s);
        // Remove element
        safeList.remove(name);
    }
}

public class ThreadSafety {
    public static void main(String[] args) throws InterruptedException {
        SafeList safeList = new SafeList();
        int workers = 10;
        Thread[] threads = new Thread[workers];

        for (int i = 0; i < workers; i++) {
            threads[i] = new Thread(new Worker(safeList, "Item-" + (i + 1)));
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("Final size: " + safeList.size());
    }
}

