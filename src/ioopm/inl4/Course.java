package ioopm.inl4;

public class Course {
    String name;
    private Book book;
    private int HP;
    private Question question;

    /**
     * Creates a course with a name, a book, and HP
     * @param name name of the course
     * @param book the book for the course
     * @param HP the amount of HP the course gives
     */

    public Course(String name, Book book, int HP){
        this.name = name;
        this.book = book;
        this.HP = HP;
    }

    /**
     * Sets the question of the course
     * @param question the new question
     */

    public void setQuestion(Question question){
        this.question = question;
    }

    /**
     * Gets the question of the course
     * @return the question of the course
     */

    public Question getQuestion(){
        return question;
    }

    /**
     * Gets the name of the course
     * @return the name of the course
     */

    public String getName(){
        return name;
    }

    /**
     * Gets the book for the course
     * @return the book for the course
     */

    public Book getBook(){
        return book;
    }

    /**
     * Gets the HP for the course
     * @return the HP for the course
     */

    public int getHP(){
        return HP;
    }

    /**
     * A string representation of the course
     * @return a string representing the course
     */

    public String toString(){
        return "Name: " + name + " Book: " + book.getName() + " HP: " + HP + "\n";
    }
}
