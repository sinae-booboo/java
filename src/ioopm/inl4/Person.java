package ioopm.inl4;

public class Person {
    private String name;
    private Room currentRoom;

    /**
     * A person with a name
     * @param name Name of the person
     */
    public Person(String name){
        this.name = name;
    }

    /**
     * Gets the name of the person
     * @return Name of the person
     */

    public String getName(){
        return name;
    }

    /**
     * Gets the person's room
     * @return the room the person is in
     */

    public Room getRoom(){
        return currentRoom;
    }

    /**
     * Moves the person to a new room
     * @param room the room to move the person to
     */

    public void setRoom(Room room){
        currentRoom = room;
    }

    /**
     * Gets the name of the person
     * @return Name of the person
     */

    public String toString(){
        return name;
    }
}
