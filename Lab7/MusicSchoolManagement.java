import java.util.ArrayList;
import java.util.List;

class MusicCourse {
    String courseCode;
    int numberOfStudents;

    public MusicCourse(String courseCode, int numberOfStudents) {
        this.courseCode = courseCode;
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseCode() {
        return courseCode;
    }
}

interface CourseFilter {
    boolean test(MusicCourse course);
}

public class MusicSchoolManagement {

    public static void main(String[] args) {
        List<MusicCourse> courses = getStaticData();

        // Using lambda expression to filter courses with more than 20 students
        List<MusicCourse> crowdedCourses = filterCourses(courses, course -> course.getNumberOfStudents() > 20);

        // Displaying crowded courses in a custom format
        System.out.println("Crowded Courses:");
        for (MusicCourse course : crowdedCourses) {
            System.out.println("Course Code: " + course.getCourseCode() + " | Students: " + course.getNumberOfStudents());
        }
    }

    private static List<MusicCourse> getStaticData() {
        List<MusicCourse> courses = new ArrayList<>();

        // Adding static data
        courses.add(new MusicCourse("M001", 15));
        courses.add(new MusicCourse("M002", 25));
        courses.add(new MusicCourse("M003", 18));

        return courses;
    }

    private static List<MusicCourse> filterCourses(List<MusicCourse> courses, CourseFilter courseFilter) {
        List<MusicCourse> filteredCourses = new ArrayList<>();
        for (MusicCourse course : courses) {
            if (courseFilter.test(course)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }
}
