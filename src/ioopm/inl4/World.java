package ioopm.inl4;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class World {
    private ArrayList<Room> rooms;
    private ArrayList<String> names;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Book> books;
    private ArrayList<Key> keys;
    private ArrayList<Course> courses;
    private ArrayList<Question> questions;
    private Sphinx sphinx;
    private Random ran;

    /**
     * Creates a world with rooms, persons and items
     * @param roomFilename Path to the .txt file containing the rooms
     * @param nameFilename Path to the .txt file containing the names
     * @param bookFilename Path to the .txt file containing the books
     * @param courseFilename Path to the .txt file containing the courses
     * @param questionFilename Path to the .txt file containing the questions
     */

    public World(String roomFilename, String nameFilename, String bookFilename, String courseFilename, String questionFilename){
        rooms     = new ArrayList<>();
        names     = new ArrayList<>();
        teachers  = new ArrayList<>();
        students  = new ArrayList<>();
        books     = new ArrayList<>();
        keys      = new ArrayList<>();
        courses   = new ArrayList<>();
        questions = new ArrayList<>();
        ran       = new Random();

        initRooms(roomFilename);
        initNames(nameFilename);
        initTeachers(16);
        initBooks(bookFilename);
        initCourses(courseFilename);
        initQuestions(questionFilename);
        initStudents(12);
        initSphinx();

    }
    private void initSphinx(){
        Room room = randRoom();
        Sphinx sphinx = new Sphinx();
        room.addSphinx(sphinx);
        sphinx.setRoom(room);

    }

    private void initCourses(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            ArrayList<Teacher> teachersCopy = new ArrayList<>(teachers);

            while ((line = buffer.readLine()) != null && teachersCopy.size() > 0) {
                String[] explodedString = line.split(";", 3);
                Book courseBook = findBook(explodedString[1]);
                if (courseBook != null) {
                    Course course = new Course(explodedString[0], courseBook,
                                    Integer.parseInt(explodedString[2]));
                    int ranIdx = ran.nextInt(teachersCopy.size());
                    teachersCopy.get(ranIdx).setCourse(course);
                    teachersCopy.remove(ranIdx);
                    courses.add(course);
                }
            }

            
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initQuestions(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split(";", 6);
                Course questionCourse = findCourse(explodedString[0]);
                if (questionCourse != null) {
                    String[] answers = {explodedString[2], explodedString[3],explodedString[4]};
                    Question question = new Question(   explodedString[1], answers,
                                                        Integer.parseInt(explodedString[5]));
                    questionCourse.setQuestion(question);
                    questions.add(question);
                }
            }

            // Done with the file
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initBooks(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split(";", 4);
                Book book = new Book(   explodedString[0], explodedString[1],
                                        Integer.parseInt(explodedString[2]),
                                        Integer.parseInt(explodedString[3]));
                randRoom().addItem(book);
                books.add(book);
            }

            // Done with the file
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initNames(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                names.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.println(filepath + " not found!");
            System.exit(0);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void initTeachers(int n_teachers){
        while (teachers.size() < n_teachers){
            int rand = ran.nextInt(names.size());
            teachers.add(new Teacher(names.remove(rand)));
        }

        for (Teacher teacher : teachers){
            Room room = randRoom();
            room.addTeacher(teacher);
            teacher.setRoom(room);
        }

    }

    private void initStudents(int n_students){
        while (students.size() < n_students){
            int rand = ran.nextInt(names.size());
            int firstCourseId = ran.nextInt(courses.size());
            int secondCourseId = ran.nextInt(courses.size());
            while (secondCourseId == firstCourseId){
                secondCourseId = ran.nextInt(courses.size());
            }
            students.add(new Student(names.remove(rand), courses.get(firstCourseId), courses.get(secondCourseId)));
        }

        for (Student student : students){
            Room room = randRoom();
            room.addStudent(student);
            student.setRoom(room);
        }

    }

    private void initRooms(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            ArrayList<String[]> roomStrings = new ArrayList<>();
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split("; ", 9);
                roomStrings.add(explodedString);
                Room room = new Room(explodedString[0]);
                rooms.add(room);
            }

            // Close file.
            buffer.close();
            inputStream.close();

            int i = 0;
            int keyCounter = 0;
            for (Room room : rooms){
                Room northRoom  = findRoom(roomStrings.get(i)[1]);
                Room eastRoom   = findRoom(roomStrings.get(i)[2]);
                Room southRoom  = findRoom(roomStrings.get(i)[3]);
                Room westRoom   = findRoom(roomStrings.get(i)[4]);

                boolean northLocked = roomStrings.get(i)[5].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[5]);
                boolean eastLocked  = roomStrings.get(i)[6].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[6]);
                boolean southLocked = roomStrings.get(i)[7].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[7]);
                boolean westLocked  = roomStrings.get(i)[8].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[8]);

                Door door;
                if (room.getDoor("north") == null) {
                    door = northRoom == null ? null : new Door(northLocked);
                    if (door != null){
                        room.setDoor(door, "north");
                        northRoom.setDoor(door, "south");
                        door.addRoom(room);
                        door.addRoom(northRoom);
                    }
                }
                if (room.getDoor("east") == null) {
                    door = eastRoom == null ? null : new Door(eastLocked);
                    if (door != null){
                        room.setDoor(door, "east");
                        eastRoom.setDoor(door, "west");
                        door.addRoom(room);
                        door.addRoom(eastRoom);
                    }
                }
                if (room.getDoor("south") == null) {
                    door = southRoom == null ? null : new Door(southLocked);
                    if (door != null){
                        room.setDoor(door, "south");
                        southRoom.setDoor(door, "north");
                        door.addRoom(room);
                        door.addRoom(southRoom);
                    }
                }
                if (room.getDoor("west") == null) {
                    door = westRoom == null ? null : new Door(westLocked);
                    if (door != null){
                        room.setDoor(door, "west");
                        westRoom.setDoor(door, "east");
                        door.addRoom(room);
                        door.addRoom(westRoom);
                    }
                }

                if (northLocked){
                    keyCounter++;
                }
                if (eastLocked){
                    keyCounter++;
                }
                if (southLocked){
                    keyCounter++;
                }
                if (westLocked){
                    keyCounter++;
                }
                i++;
            }

            keyCounter = (int)Math.ceil(keyCounter*1.5);
            for (int j = 0; j < keyCounter; j++) {
                randRoom().addItem(new Key());
            }

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the room with the given name
     * @param name name of the room
     * @return the room with the given name. If it doesn't exist then return null
     */

    public Room findRoom(String name){
        for (Room room : rooms){
            if (room.getName().equals(name)){
                return room;
            }
        }
        return null;
    }

    /**
     * Gets the book with the given name
     * @param name name of the book
     * @return the book with the given name. If it doesn't exist then return null
     */

    public Book findBook(String name){
        for (Book book : books){
            if (book.getName().toLowerCase().equals(name.toLowerCase())){
                return book;
            }
        }
        return null;
    }

    /**
     * Gets the course with the given name
     * @param name name of the course
     * @return the course with the given name. If it doesn't exist then return null
     */

    public Course findCourse(String name){
        for (Course course : courses){
            if (course.getName().toLowerCase().equals(name.toLowerCase())){
                return course;
            }
        }
        return null;
    }

    /**
     * A random room
     * @return a random room
     */

    public Room randRoom(){
        return rooms.get(ran.nextInt(rooms.size()));
    }

    /**
     * Gets all courses in the world
     * @return all courses in the world
     */

    public ArrayList<Course> getCourses(){
        return courses;
    }

    /**
     * Moves all the persons (except the player and persons in the same room as the player) to new random rooms
     */

    public void movePersons(Player player){
        Room room;
        for (Teacher teacher : teachers){
            if (teacher.getRoom() != player.getRoom()) {
                room = randRoom();
                room.addTeacher(teacher);
                teacher.getRoom().removeTeacher(teacher);
                teacher.setRoom(room);
            }
        }
        for (Student student : students){
            if (student.getRoom() != player.getRoom()) {
                room = randRoom();
                room.addStudent(student);
                student.getRoom().removeStudent(student);
                student.setRoom(room);
            }
        }
        room = randRoom();
        if (room.hasSphinx() && player.getRoom() != room){
            Room newRoom = randRoom();
            Sphinx sphinx = room.getSphinx();
            newRoom.addSphinx(sphinx);
            sphinx.setRoom(newRoom);
            room.removeSphinx();
        }
    }

    /**
     * Gets a string representation the world
     * @return a string representing each room in the world
     */

    public String toString(){
        String result = "";
        for (Room room : rooms) {
            result += room + "\n------------------\n";
        }
        return result;
    }
}
