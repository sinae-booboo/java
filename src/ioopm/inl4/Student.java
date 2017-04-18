package ioopm.inl4;

public class Student extends Person{
    private Course currentCourse;
    private Course finishedCourse;

    /**
     * Creates a student with a name, a current course and a finished course
     * @param name name of the student
     * @param currentCourse the student's current course
     * @param finishedCourse the student's finished course
     */

    public Student(String name, Course currentCourse, Course finishedCourse){
        super(name);
        this.currentCourse  = currentCourse;
        this.finishedCourse = finishedCourse;
    }

    /**
     * Gets the student's current course
     * @return the current course
     */

    public Course getCurrentCourse(){
        return currentCourse;
    }

    /**
     * Gets the student's finished course
     * @return the finished course
     */

    public Course getFinishedCourse(){
        return finishedCourse;
    }

    /**
     * Gets the name, finished course and current course
     * @return A string representing the name, finished course and current course
     */

    public String toString(){
        return super.toString() + "\nFinished Course: " + finishedCourse.getName() + " (Book: " + finishedCourse.getBook().getName() +")\n" +
                "Current Course: " + currentCourse.getName() + " (Book: " + currentCourse.getBook().getName() + ")";
    }
}
