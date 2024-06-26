package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.OpinionPoll;

public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        // John - 48
        return String.format("%s - %d", this.name, this.age);
    }

    @Override
    public int compareTo(Person person) {
        return this.getName().compareTo(person.getName());
    }
}

