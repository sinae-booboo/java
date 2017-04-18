package ioopm.inl4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Room implements ItemContainer{
    private String name;
    private Door NORTH;
    private Door SOUTH;
    private Door EAST;
    private Door WEST;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Item> items;
    private Sphinx sphinx;

    /**
     * Creates an empty room with the given name
     * @param name name of the room
     */

    public Room(String name){
        this.name = name;
        items       = new ArrayList<>();
        teachers    = new ArrayList<>();
        students    = new ArrayList<>();
        sphinx      = null;
    }

    /**
     * Adds a sphinx to the room
     * @param s the sphinx to add
     */

    public void addSphinx(Sphinx s){
        sphinx = s;
    }

    /**
     * Removes the sphinx from the room
     */

    public void removeSphinx(){
        sphinx = null;
    }

    /**
     * Checks if a sphinx exists in the room
     * @return true if a sphinx exists in the room, else false
     */

    public boolean hasSphinx(){
        return sphinx != null;
    }

    /**
     * Gets the sphinx in the room
     * @return The sphinx in the room, null if there is none
     */

    public Sphinx getSphinx(){
        return sphinx;
    }

    /**
     * Adds an item to the room
     * @param item the item to add
     */

    public void addItem(Item item){
        items.add(item);
    }

    /**
     * Adds a student to the room
     * @param student the student to add
     */

    public void addStudent(Student student){
        students.add(student);
    }

    /**
     * Removes a student from the room
     * @param student the student to remove
     */

    public void removeStudent(Student student){
        students.remove(student);
    }

    /**
     * Gets the students in the room
     * @return the students in the room
     */

    public ArrayList<Student> getStudents(){
        return students;
    }

    /**
     * Adds a teacher to the room
     * @param teacher teacher to add
     */

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    /**
     * Removes a teacher from the room
     * @param teacher the teacher to remove
     */

    public void removeTeacher(Teacher teacher){
        teachers.remove(teacher);
    }

    /**
     * Gets the teachers in the room
     * @return the teachers in the room
     */

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    /**
     * The teachers in the room asks the question for the course if (75% chance) the player currently has the course or (50% chance)
     * the player has finished the course. If it's a current course and the player answers the question correctly then finish the course,
     * else nothing happens. If it's a finished course and the player answers the question correctly then nothing happens,
     * else the course is removed from the player's finished courses and the player loses the courses HP.
     * @param player the player to ask
     */

    public void askQuestions(Player player){
        ArrayList<Teacher> teachers = getTeachers();
        for (Teacher teacher : teachers){
            Course course = teacher.getCourse();
            Scanner scanner = new Scanner(System.in);
            Random ran = new Random();
            if (player.getUnfinishedCourses().contains(course)){
                if (ran.nextInt(4) < 3){ //75% chance
                    System.out.print(teacher.getName() + " asks: ");
                    System.out.println(course.getQuestion().ask(player.getInventory().contains(course.getBook())));
                    System.out.print("Answer #: ");
                    int input = 0;
                    while(input < 1 || input > 3) {
                        try {
                            input = Integer.parseInt(scanner.nextLine());
                            if (input-1 == course.getQuestion().getCorrectAnswerIdx()){
                                System.out.println("Oh yes!\n");
                                player.finishCourse(course);
                            }else if (input < 1 || input > 3){
                                System.out.println("Very bad input, try again");
                            }else{
                                System.out.println("\nNope!\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Very bad input, try again");
                        }
                    }
                }
            }else if(player.getFinishedCourses().contains(course)){
                if (ran.nextInt(2) == 0){ //50% chance
                    System.out.print(teacher.getName() + " asks: ");
                    System.out.println(course.getQuestion().ask(player.getInventory().contains(course.getBook())));
                    System.out.print("Answer #: ");
                    int input = 0;
                    while(input < 1 || input > 3) {
                        try {
                            input = Integer.parseInt(scanner.nextLine());
                            if (input-1 == course.getQuestion().getCorrectAnswerIdx()){
                                System.out.println("Oh yes!\n");
                            }else if (input < 1 || input > 3){
                                System.out.println("Very bad input, try again\n");
                            }else{
                                System.out.println("\nNope!\n");
                                player.removeFinishedCourse(course);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Very bad input, try again\n");
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the name of the room
     * @return the name of the room
     */

    public String getName(){
        return this.name;
    }

    /**
     * Set the door at the given direction
     * @param door the new door
     * @param direction the direction to set the door [north, east, south or west]
     */

    public void setDoor(Door door, String direction){
        switch(direction.toLowerCase()){
            case "north":
                this.NORTH = door;
                break;
            case "east":
                this.EAST = door;
                break;
            case "south":
                this.SOUTH = door;
                break;
            case "west":
                this.WEST = door;
                break;
        }
    }

    /**
     * Get the door at the given direction
     * @param direction the direction the door is at [north, east, south or west]
     * @return the door at the given direction
     */

    public Door getDoor(String direction){
        switch(direction.toLowerCase()){
            case "north":
                return NORTH;
            case "east":
                return EAST;
            case "south":
                return SOUTH;
            case "west":
                return WEST;
            default:
                return null;
        }
    }

    /**
     * Finds a student in the room with the given name
     * @param name name of the student
     * @return the student with the given name, null if there is none
     */

    public Student findStudent(String name){
        for (Student student : students){
            if (student.getName().toLowerCase().equals(name.toLowerCase())){
                return student;
            }
        }
        //System.out.println(name + " is not in this room");
        return null;
    }

    /**
     * Finds an item in the room with the given name
     * @param name name of the item
     * @return the item with the given name, null if there is none
     */

    public Item findItem(String name){
        for (Item item: items){
            if (item.getName().toLowerCase().equals(name.toLowerCase())){
                return item;
            }
        }
        System.out.println(name + " is not in this room");
        return null;
    }

    /**
     * Removes the given item from the room
     * @param item the item to remove
     */

    public void removeItem(Item item){
        items.remove(item);
    }

    /**
     * Gets a string representing of the room
     * @return a string representation of the room
     */

    public String toString(){
        String roomDesc = this.name +
                "\nNORTH: " + ((NORTH == null) ? "X" : NORTH.otherRoom(this).getName()  + " Locked: " + NORTH.isLocked()) +
                "\nEAST: "  + ((EAST  == null) ? "X" : EAST.otherRoom(this).getName()   + " Locked: " + EAST.isLocked()) +
                "\nSOUTH: " + ((SOUTH == null) ? "X" : SOUTH.otherRoom(this).getName()  + " Locked: " + SOUTH.isLocked()) +
                "\nWEST: "  + ((WEST  == null) ? "X" : WEST.otherRoom(this).getName()   + " Locked: " + WEST.isLocked()) + "\n";

        String itemString = "\nItems: \n";
        for(Item item : items){
            itemString += item + "\n";
        }

        String teacherString = "\nTeachers: \n";
        for(Teacher t : teachers){
            teacherString += t.toString() + "\n\n";
        }

        String studentString = "\nStudents: \n";
        for(Student s : students){
            studentString += s.toString() + "\n\n";
        }
        String sphinxString = "";

        if (sphinx!=null){
           sphinxString = "\nThe sphinx is in this room";
        }

        return roomDesc + itemString + teacherString + studentString + sphinxString + "\n\n----------------\n\n";
    }
}
