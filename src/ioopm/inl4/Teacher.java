package ioopm.inl4;

import java.util.ArrayList;

public class Teacher extends Person {
    private Course course;

    /**
     * Creates a teacher with a name
     * @param name Name of the teacher
     */

    public Teacher(String name){
        super(name);
    }

    /**
     * Creates a teacher with a name and a course
     * @param name Name of the teacher
     * @param course The teacher's course
     */

    public Teacher(String name, Course course){
        super(name);
        this.course = course;
    }

    /**
     * Sets the teacher's course
     * @param course The new course
     */

    public void setCourse(Course course){
        this.course = course;
    }

    /**
     * Gets the teacher's course
     * @return the teacher's course
     */

    public Course getCourse(){
        return course;
    }

    /**
     * Gets the name and course (if any) of the teacher
     * @return a string representing the name and course of the teacher
     */

    public String toString(){
        return super.toString() + "\nCourse: " + ((course == null) ? " none" : course.getName() + " (Book: " + course.getBook().getName() + ")");
    }

}
