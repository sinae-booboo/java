package ioopm.inl4.tests;

import ioopm.inl4.*;
import org.junit.Test;
import org.junit.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    World world = new World("txt/testWorld.txt", "txt/names.txt", "txt/books.txt", "txt/courses.txt", "txt/questions.txt");
    Room room = world.findRoom("Corridor 1");
    Course course = world.findCourse("Cooking 101");
    Book book = new Book("Rutiga kokboken", "Ica", 2016);
    Teacher teacher = new Teacher("Hello Kitty", course);
    ArrayList<Course> courses = new ArrayList<>();
    Player player = new Player("Jag", courses, room);

    @Test
    public void testGo() throws Exception {

        player.go("north");
        System.out.println(player.getRoom());
        assertEquals(player.getRoom(), world.findRoom("Room 2"));
        player.go("south");
        assertEquals(player.getRoom(), room);

        player.go("east");
        assertEquals(player.getRoom(), world.findRoom("Corridor 2"));
        player.go("west");
        assertEquals(player.getRoom(), room);

        player.go("west");
        assertEquals(player.getRoom(), world.findRoom("Room 1"));
        player.go("east");
        assertEquals(player.getRoom(), room);

        player.go("south");
        assertEquals(player.getRoom(), world.findRoom("Room 3"));
        player.go("north");
        assertEquals(player.getRoom(), room);
    }

    @Test
    public void testUnlock() throws Exception {
        player.go("east");
        player.unlock("south");
        assertEquals(player.getRoom().getDoor("south").isLocked(), true);

        player.getInventory().addItem(new Key());
        player.unlock("south");
        assertEquals(player.getRoom().getDoor("south").isLocked(), false);
        player.go("south");
        assertEquals(player.getRoom(), world.findRoom("Room 6"));
    }

    @Test
    public void testPickup() throws Exception {
        player.getRoom().addItem(book);
        player.pickup("Rutiga kokboken!");
        assertEquals(player.getInventory().findItem("Rutiga kokboken") != null &&
                player.getRoom().findItem("Rutiga kokboken") == null, true);
        player.drop("Rutiga kokboken");
        player.getRoom().removeItem(book);
    }

    @Test
    public void testDrop() throws Exception {
        player.getRoom().addItem(book);
        player.pickup("Rutiga kokboken");
        player.drop("Rutiga kokboken");

        assertEquals(player.getInventory().findItem("Rutiga kokboken") == null &&
                player.getRoom().findItem("Rutiga kokboken") != null, true);
        player.getRoom().removeItem(book);
    }

    @Test
    public void testEnroll() throws Exception {
        room.addTeacher(teacher);
        player.enroll(course);
        assertEquals(player.getUnfinishedCourses().contains(course), true);
    }
}
