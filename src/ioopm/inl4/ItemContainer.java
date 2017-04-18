package ioopm.inl4;

public interface ItemContainer <T extends Item>{
    public void addItem(T item) throws Inventory.InventoryFullException;
    public void removeItem(T item);
    public T findItem(String name);
}
