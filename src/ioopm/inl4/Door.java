package ioopm.inl4;

import java.util.ArrayList;

public class Door {
    private boolean locked;
    private ArrayList<Room> rooms;

    /**
     * A door which is either locked or not
     * @param locked true if the door is locked, else false
     */

    public Door(boolean locked){
        this.locked = locked;
        rooms = new ArrayList<>();
    }

    /**
     * Checks if the door is locked
     * @return true if the door is locked, else false
     */

    public boolean isLocked(){
        return this.locked;
    }

    /**
     * Adds a room to the door
     * @param room the new room
     */

    public void addRoom(Room room){
        if (!rooms.contains(room) && rooms.size() < 2) {
            rooms.add(room);
        }
    }

    /**
     * Gets the room on the other side of the door
     * @param room A room on either side of the door
     * @return the room on the opposite side from the given room
     */

    public Room otherRoom(Room room){
        return rooms.get(0) == room ? rooms.get(1) : rooms.get(0);
    }

    /**
     * Unlocks the door
     */

    public void unlock(){
        this.locked = false;
    }
}
