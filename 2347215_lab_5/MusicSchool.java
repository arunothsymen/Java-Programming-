import java.util.Scanner;

public class MusicSchool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("**********************************");
        System.out.println("Welcome to the Music School Management System!");
        System.out.println("**********************************");

        while (true) {
            System.out.print("Enter the name of the student: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter the name of the teacher: ");
            String teacherName = scanner.nextLine();

            System.out.print("Enter the name of the music class: ");
            String className = scanner.nextLine();

            // Create threads and start
            Student student = new Student(studentName);
            Teacher teacher = new Teacher(teacherName);
            MusicClass musicClass = new MusicClass(className);

            student.start();
            teacher.start();
            musicClass.start();

            // Wait for threads to complete
            try {
                student.join();
                teacher.join();
                musicClass.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("**********************************");
            System.out.println("Do you want to add another session? (yes/no): ");
            String userInput = scanner.nextLine().toLowerCase();
            if (!userInput.equals("yes")) {
                break; // Exit the loop if the user doesn't want to add more sessions
            }
        }

        // Perform other operations after all sessions are complete...
        System.out.println("**********************************");
        System.out.println("Thank you for using the Music School Management System.");
        System.out.println("All sessions are complete. Have a musical day!");
        System.out.println("**********************************");

        scanner.close();
    }
}
