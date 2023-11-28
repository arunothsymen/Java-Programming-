// File: MusicSchool.java
package symen.lab4.assignment;

import java.util.ArrayList;
import java.util.List;

public class MusicSchool {
    private String schoolName;
    private List<MusicTeacher> teachers;
    private List<MusicStudent> students;

    public MusicSchool(String schoolName) {
        this.schoolName = schoolName;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void enrollStudent(MusicStudent student) {
        students.add(student);
        System.out.println("Student enrolled: " + student.getName());
    }

    public void assignTeacher(MusicStudent student, MusicTeacher teacher) {
        student.setTeacher(teacher);
        System.out.println("Teacher assigned to student: " + teacher.getName() + " to " + student.getName());
    }

    public List<MusicStudent> getStudents() {
        return students;
    }

    public void displayInfo() {
        System.out.println("Music School: " + schoolName);
        System.out.println("Teachers:");
        for (MusicTeacher teacher : teachers) {
            System.out.println("- " + teacher.getName());
        }
        System.out.println("Students:");
        for (MusicStudent student : students) {
            System.out.println("- " + student.getName() + " (Teacher: " + student.getTeacher().getName() + ")");
        }
    }
}
