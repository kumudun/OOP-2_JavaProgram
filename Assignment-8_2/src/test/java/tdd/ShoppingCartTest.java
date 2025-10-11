
package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ShoppingCart TDD tests")
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    @DisplayName("Add items increases count")
    void testAddItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        assertEquals(2, cart.getItemCount());
    }

    @Test
    @DisplayName("Remove item by name removes one occurrence")
    void testRemoveItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        assertTrue(cart.removeItem("Apple"), "Should remove existing item");
        assertEquals(1, cart.getItemCount());
    }

    @Test
    @DisplayName("Calculate total sums prices")
    void testCalculateTotal() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);
        assertEquals(2.25, cart.calculateTotal(), 0.0001);
    }

    @Test
    @DisplayName("Removing a missing item returns false and does not change count")
    void removeMissingItem() {
        cart.addItem("Apple", 1.0);
        assertFalse(cart.removeItem("Banana"));
        assertEquals(1, cart.getItemCount());
    }

    @Test
    @DisplayName("Reject null/blank names and negative prices")
    void inputValidation() {
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(null, 1.0));
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(" ", 1.0));
        assertThrows(IllegalArgumentException.class, () -> cart.addItem("Apple", -0.01));
    }

    @Test
    @DisplayName("Duplicate names are allowed; remove removes only one")
    void duplicates() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 2.0);
        assertEquals(2, cart.getItemCount());
        assertTrue(cart.removeItem("Apple"));
        assertEquals(1, cart.getItemCount());
        // Remaining total should be the price of one "Apple"
        double total = cart.calculateTotal();
        assertTrue(total == 1.0 || total == 2.0);
    }
}
