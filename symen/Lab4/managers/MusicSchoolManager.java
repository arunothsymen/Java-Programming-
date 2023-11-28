// File: MusicSchoolManager.java
package symen.lab4.managers;

import symen.lab4.assignment.MusicSchool;
import symen.lab4.assignment.MusicStudent;
import symen.lab4.assignment.MusicTeacher;

public class MusicSchoolManager {
    public static void main(String[] args) {
        MusicSchool musicSchool = new MusicSchool("Harmony Music School");

        MusicTeacher teacher1 = new MusicTeacher("Mr. Smith");
        MusicTeacher teacher2 = new MusicTeacher("Ms. Davis");

        musicSchool.enrollStudent(new MusicStudent("Alice"));
        musicSchool.enrollStudent(new MusicStudent("Bob"));

        musicSchool.assignTeacher(musicSchool.getStudents().get(0), teacher1);
        musicSchool.assignTeacher(musicSchool.getStudents().get(1), teacher2);

        musicSchool.displayInfo();
    }
}
