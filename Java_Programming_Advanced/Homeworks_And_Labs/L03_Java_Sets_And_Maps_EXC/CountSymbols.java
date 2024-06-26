package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        Map<Character, Integer> occurrences = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (!occurrences.containsKey(symbol)) {
                occurrences.put(symbol, 1);
            } else {
                occurrences.put(symbol, occurrences.get(symbol) + 1);
            }
        }
        occurrences.forEach((k, v) -> System.out.printf("%c: %d time/s\n", k, v));

    }
}
