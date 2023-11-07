class Student {
    private String name;
    private int age;

    public Student() {
        name = "Unknown";
        age = 0;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void playInstrument() {
        System.out.println(name + " is playing an instrument.");
    }

    public void playInstrument(String instrument) {
        System.out.println(name + " is playing the " + instrument + ".");
    }

    public void enrollInClass(String className) {
        System.out.println(name + " is enrolled in the " + className + " class.");
    }
}

class MusicSchoolManagement {
    public static void main(String[] args) {
        Student student1 = new Student("Sam", 15);
        Student student2 = new Student("Symen", 12);
        Student student3 = new Student();

        student3.setName("Hanna");
        student3.setAge(10);

        student1.playInstrument();
        student2.playInstrument("Piano");
        student3.enrollInClass("Guitar");

        System.out.println(student1.getName() + " is " + student1.getAge() + " years old.");
        System.out.println(student2.getName() + " is " + student2.getAge() + " years old.");
        System.out.println(student3.getName() + " is " + student3.getAge() + " years old.");
    }
}