package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class GraterNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());

        if(num1 > num2) {
            System.out.println(num1);
        } else {
            System.out.println(num2);
        }
    }
}
