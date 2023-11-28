// File: MusicStudent.java
package symen.lab4.assignment;

public class MusicStudent {
    private String name;
    private MusicTeacher teacher;

    public MusicStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MusicTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(MusicTeacher teacher) {
        this.teacher = teacher;
    }
}
