package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        String pw = scanner.nextLine();
        String input = scanner.nextLine();

        while(!input.equals(pw)) {
            input = scanner.nextLine();
        }
        System.out.printf("Welcome %s!", (name));
    }
}
