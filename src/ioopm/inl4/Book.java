package ioopm.inl4;

public class Book extends Item{
    private String author;
    private int year;

    /**
     * Creates a book with a name, author and year
     * @param name name of the book
     * @param author author of the book
     * @param year the year the book was released
     */

    public Book(String name, String author, int year){
        super(name);
        this.author = author;
        this.year = year;
    }

    /**
     * Creates a book with a name, author, year and inventory space
     * @param name name of the book
     * @param author author of the book
     * @param year the year the book was released
     * @param space the amount of space the book takes in the inventory
     */

    public Book(String name, String author, int year, int space){
        super(space, name);
        this.author = author;
        this.year = year;
    }

    /**
     * Gets a string representing of the book
     * @return a string representation of the book
     */

    public String toString(){
        return "\nBook:\nTitle: " + getName() + "\nYear: " + year + "\nAuthor: " + author + "\nWeight: " + getSpace() + "\n";
    }
}
