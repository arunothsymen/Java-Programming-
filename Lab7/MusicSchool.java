package musicschool;

import java.util.List;
import java.util.ArrayList;

interface StudentFilter {
    boolean filter(Student student);
}

public class MusicSchool {
    private static List<Student> students = new ArrayList<>();

    // Method overloading
    public static void addStudent(Student student) {
        students.add(student);
    }

    public static List<Student> filterStudents(StudentFilter filter) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (filter.filter(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    // Method overloading
    public static void addStudent(Student student) {
        students.add(student);
    }

    public static List<Student> filterStudents(StudentFilter filter) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (filter.filter(student)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

public static void main(String[] args) {
    // Creating students and courses
    Student student1 = new Student("John", 20, "Guitar");
    Student student2 = new Student("Alice", 22, "Piano");

    Course course1 = new Course("Guitar Basics", "Instructor1");
    Course course2 = new Course("Piano Fundamentals", "Instructor2");

    // Adding students to the music school
    MusicSchool.addStudent(student1);
    MusicSchool.addStudent(student2);

    // Displaying student information
    System.out.println("All Students:");
    for (Student student : students) {
        student.displayInfo();
    }

    // Using lambda expression for filtering students
    List<Student> guitarStudents = MusicSchool.filterStudents(s -> s.instrument.equals("Guitar"));

    // Displaying filtered students
    System.out.println("\nGuitar Students:");
    for (Student student : guitarStudents) {
        student.displayInfo();
    }
}