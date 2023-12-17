import java.util.concurrent.TimeUnit;

public class Teacher extends Thread {
    private String teacherName;

    public Teacher(String name) {
        this.teacherName = name;
    }

    public void run() {
        System.out.println("Teacher " + teacherName + " is ready to teach.");

        try {
            TimeUnit.SECONDS.sleep(3); // Simulating some teaching activity
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Teacher " + teacherName + " has finished teaching.");
    }
}
