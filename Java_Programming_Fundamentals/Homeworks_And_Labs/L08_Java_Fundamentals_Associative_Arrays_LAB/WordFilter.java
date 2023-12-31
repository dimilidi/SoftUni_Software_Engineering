package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class WordFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = Arrays
                .stream(scanner.nextLine().split(" "))
                .filter(word -> word.length() % 2 == 0)
                .toArray(String[]::new);
        System.out.println(String.join(System.lineSeparator(), words));
    }
}
