package ioopm.inl4.tests;

import ioopm.inl4.Inventory;
import ioopm.inl4.Key;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

    Inventory inventory = new Inventory();
    Key key = new Key();

    @Test
    public void testAddItem() throws Exception {
        inventory.addItem(key);
        assertEquals(inventory.contains(key), true);
    }

    @Test
    public void testRemoveItem() throws Exception {
        inventory.addItem(key);
        inventory.removeItem(key);
        assertEquals(inventory.contains(key), false);
    }

    @Test
    public void testFindItem() throws Exception {
        inventory.addItem(key);
        assertEquals(inventory.findItem("key") != null, true);
    }
}
