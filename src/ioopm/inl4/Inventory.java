package ioopm.inl4;

import java.util.ArrayList;

public class Inventory <T extends Item> implements ItemContainer <T>{
    private int capacity;
    private ArrayList<T> items;
    private Log log;

    /**
     * Creates an empty inventory with a capacity of 10
     */

    public Inventory(){
        items = new ArrayList<>();
        capacity = 10;
        log = new Log();
    }

    /**
     * Adds an item (if it fits) to the inventory
     * @param item the item to add
     * @return true if item was added, false otherwise.
     */

    public void addItem(T item) throws InventoryFullException{
        if (capacity - item.getSpace() < 0){
            throw new InventoryFullException("Det är fullt!, Töm!");
        }else{
            items.add(item);
            capacity -= item.getSpace();
            System.out.println(item.getName() + " is picked up\n");
            log.addMessage(item.getName() + " added");
        }
    }

    /**
     * Removes an item from the inventory
     * @param item the item to remove
     */

    public void removeItem(T item){
        if (items.contains(item)) {
            items.remove(item);
            log.addMessage(item.getName() + " removed");
        }
    }

    /**
     * Finds an item with the given name in the inventory
     * @param name name of the item
     * @return the item with the given name, null if it doesn't exist
     */

    public T findItem(String name){
        for (T item : items){
            if (item.getName().toLowerCase().equals(name.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if the item is in the inventory
     * @param item the item to check for
     * @return true if the item is in the inventory, else false
     */
    public boolean contains(T item){
        return items.contains(item);
    }


    /**
     * Lists the items in the inventory
     * @return a string representation of the inventory
     */

    public String toString(){
        String result = "\n";
        if (items.size() != 0) {
            for (T item : items) {
                result = result.concat(item + "\n");
            }
        }else{
            return "Empty!";
        }
        return result;
    }

    public void printLog(){
        System.out.println(log);
    }

    public static class InventoryFullException extends Exception{

        public InventoryFullException(){
            super();
        }

        public InventoryFullException(String message){
            super(message);
        }
    }

    private class Log{
        ArrayList<String> msgs;

        public Log(){
            msgs = new ArrayList<>();
        }

        public void addMessage(String msg){
            msgs.add(msg);
        }

        public String toString(){
            String result = "";
            for (int i = 0; i < msgs.size(); i++) {
                result += "#" + (i+1) + ": " + msgs.get(i) + "\n";
            }
            return result;
        }
    }
}
