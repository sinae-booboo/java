package ioopm.inl4.tests;

import ioopm.inl4.Door;
import ioopm.inl4.Room;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoorTest {
    Door door = new Door(true);
    Room room1 = new Room("room1");
    Room room2 = new Room("room2");

    @Test
    public void testOtherRoom() throws Exception {
        door.addRoom(room1);
        door.addRoom(room2);
        assertEquals(door.otherRoom(room1), room2);
    }

    @Test
    public void testOtherRoomOpposite() throws Exception {
        door.addRoom(room1);
        door.addRoom(room2);
        assertEquals(door.otherRoom(room2), room1);
    }

    @Test
    public void testUnlock() throws Exception {
        door.unlock();
        assertEquals(door.isLocked(), false);
    }
}
