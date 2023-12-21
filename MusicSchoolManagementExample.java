import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Generic interface representing an orderable item
interface Orderable {
    String getName();
    double getPrice();
}

// Generic class representing a MusicCourse
class MusicCourse<T extends Orderable> {
    private List<T> courseDetails;

    public MusicCourse() {
        this.courseDetails = new ArrayList<>();
    }

    public void addCourseDetails(T courseDetail) {
        this.courseDetails.add(courseDetail);
    }

    public void removeCourseDetails(T courseDetail) {
        this.courseDetails.remove(courseDetail);
    }

    public List<T> getCourseDetails() {
        return courseDetails;
    }

    public void displayCourseDetails() {
        System.out.println("\nMusic Course Details:");
        for (T detail : courseDetails) {
            System.out.println("Course: " + detail.getName());
            System.out.println("Price: $" + detail.getPrice());
            System.out.println();
        }
    }
}

// Generic interface for music-related functionality
interface MusicFunctionality {
    void playLesson();
}

// Concrete class representing a music lesson
class MusicLesson implements Orderable, MusicFunctionality {
    private String lessonName;
    private double price;

    public MusicLesson(String lessonName, double price) {
        this.lessonName = lessonName;
        this.price = price;
    }

    @Override
    public String getName() {
        return lessonName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void playLesson() {
        System.out.println("Playing lesson: " + lessonName);
    }
}

// Generic class representing a MusicStudent
class MusicStudent<T extends Orderable> {
    private List<T> studentDetails;

    public MusicStudent() {
        this.studentDetails = new ArrayList<>();
    }

    public void addStudentDetails(T studentDetail) {
        this.studentDetails.add(studentDetail);
    }

    public void removeStudentDetails(T studentDetail) {
        this.studentDetails.remove(studentDetail);
    }

    public List<T> getStudentDetails() {
        return studentDetails;
    }

    public void displayStudentDetails() {
        System.out.println("\nMusic Student Details:");
        for (T detail : studentDetails) {
            System.out.println("Student: " + detail.getName());
            System.out.println("Tuition Fee: $" + detail.getPrice());
            System.out.println();
        }
    }
}

// Concrete class representing a music student
class MusicStudentInfo implements Orderable {
    private String studentName;
    private double tuitionFee;

    public MusicStudentInfo(String studentName, double tuitionFee) {
        this.studentName = studentName;
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String getName() {
        return studentName;
    }

    @Override
    public double getPrice() {
        return tuitionFee;
    }
}

public class MusicSchoolManagementExample {
    public static void main(String[] args) {
        MusicCourse<MusicLesson> musicCourse = new MusicCourse<>();
        MusicStudent<MusicStudentInfo> musicStudent = new MusicStudent<>();

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Add a lesson");
            System.out.println("2. Add a student");
            System.out.println("3. Display course details");
            System.out.println("4. Display student details");
            System.out.println("5. Play a lesson");
            System.out.println("6. Exit");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    musicCourse.addCourseDetails(createLesson());
                    break;
                case 2:
                    musicStudent.addStudentDetails(createStudent());
                    break;
                case 3:
                    musicCourse.displayCourseDetails();
                    break;
                case 4:
                    musicStudent.displayStudentDetails();
                    break;
                case 5:
                    playLesson(musicCourse);
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static MusicLesson createLesson() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter lesson name: ");
        String name = scanner.nextLine();

        System.out.print("Enter lesson price: $");
        double price = scanner.nextDouble();

        return new MusicLesson(name, price);
    }

    private static MusicStudentInfo createStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter tuition fee: $");
        double tuitionFee = scanner.nextDouble();

        return new MusicStudentInfo(name, tuitionFee);
    }

    private static void playLesson(MusicCourse<MusicLesson> musicCourse) {
        System.out.println("Select a lesson to play:");
        List<MusicLesson> lessons = musicCourse.getCourseDetails();

        if (lessons.isEmpty()) {
            System.out.println("No lessons available.");
            return;
        }

        for (int i = 0; i < lessons.size(); i++) {
            System.out.println((i + 1) + ". " + lessons.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the lesson to play: ");
        int lessonNumber = scanner.nextInt();

        if (lessonNumber > 0 && lessonNumber <= lessons.size()) {
            MusicLesson selectedLesson = lessons.get(lessonNumber - 1);
            selectedLesson.playLesson();
        } else {
            System.out.println("Invalid lesson number.");
        }
    }
}
