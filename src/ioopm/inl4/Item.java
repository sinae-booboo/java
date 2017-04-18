package ioopm.inl4;

public abstract class Item {
    private int inventorySpace;
    private String name;

    /**
     * Creates an item with a given name
     * @param name the name of the item
     */

    public Item(String name){
        this.name = name;
    }

    /**
     * Creates an item with a given space and name
     * @param space the inventory space of the item
     * @param name the name of the item
     */

    public Item(int space, String name){
        inventorySpace = space;
        this.name = name;
    }

    /**
     * Gets the inventory space the item takes
     * @return the inventory space the item takes
     */

    public int getSpace(){
        return inventorySpace;
    }

    /**
     * Sets the inventory space the item takes
     * @param space the new amount of space the item takes
     */

    public void setSpace(int space){
        inventorySpace = space;
    }

    /**
     * Gets the name of the item
     * @return the name of the item
     */

    public String getName(){
        return name;
    }

    /**
     * Checks if the given item has the same name and takes up as much space as the instance item
     * @param item item to compare to
     * @return true if they have the same values, else false
     */

    public boolean equals(Item item){
        return name.equals(item.name) && inventorySpace == item.inventorySpace;
    }

    /**
     * Gets the name of the item
     * @return the name of the item
     */

    public String toString(){
        return name;
    }
}
