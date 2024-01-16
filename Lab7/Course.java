public class Course {
    package musicschool;

public class Course {
    String courseName;
    String instructor;

    Course(String courseName, String instructor) {
        this.courseName = courseName;
        this.instructor = instructor;
    }

    void displayInfo() {
        System.out.println("Course: " + courseName + ", Instructor: " + instructor);
    }
}
}
