package musicschool;

abstract class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void displayInfo();
}

public class Student extends Person {
    String instrument;

    Student(String name, int age, String instrument) {
        super(name, age);
        this.instrument = instrument;
    }

    void displayInfo() {
        System.out.println("Student: " + name + ", Age: " + age + ", Instrument: " + instrument);
    }
}