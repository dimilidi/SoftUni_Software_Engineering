package Homeworks_And_Labs.L01_ProgramingBasicsWithJava_LAB;

import java.util.Scanner;

public class GreetingByName {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            System.out.println("Hello, " + name + "!");
    }
}
