package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class PoundsToDollars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double pounds = Double.parseDouble(scanner.nextLine());

        scanner.close();

        double dollars = pounds * 1.36;

        System.out.printf("%.3f", dollars);
    }
}
