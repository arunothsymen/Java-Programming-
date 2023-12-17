import java.util.concurrent.TimeUnit;

public class Student extends Thread {
    private String studentName;

    public Student(String name) {
        this.studentName = name;
    }

    public void run() {
        System.out.println("Student " + studentName + " has joined the music school.");

        try {
            TimeUnit.SECONDS.sleep(2); // Simulating some student activity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Student " + studentName + " has completed their practice.");
    }
}
