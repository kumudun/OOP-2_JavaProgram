// src/main/java/tdd/ShoppingCart.java
package tdd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {

    private static final class Item {
        final String name;
        final double price;
        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private final List<Item> items = new ArrayList<>();

    /**
     * Adds an item with given name and non-negative price.
     * @throws IllegalArgumentException if name is null/blank or price < 0
     */
    public void addItem(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name must not be null/blank");
        }
        if (price < 0.0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        items.add(new Item(name.trim(), price));
    }

    /**
     * Removes a single occurrence of an item by name (first match).
     * @return true if an item was removed; false otherwise.
     */
    public boolean removeItem(String name) {
        if (name == null) return false;
        String target = name.trim();
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().name.equals(target)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /** @return number of items currently in the cart. */
    public int getItemCount() {
        return items.size();
    }

    /** @return sum of item prices. */
    public double calculateTotal() {
        double sum = 0.0;
        for (Item it : items) {
            sum += it.price;
        }
        return sum;
    }
}


