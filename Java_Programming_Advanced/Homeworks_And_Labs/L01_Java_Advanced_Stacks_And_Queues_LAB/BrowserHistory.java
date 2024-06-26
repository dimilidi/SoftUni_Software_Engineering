package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_LAB;

import java.util.ArrayDeque;
import java.util.Scanner;
public class BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> history = new ArrayDeque<>();

        String line = scanner.nextLine();
        String prevUrl = "no previous URLs";
        history.push(prevUrl);

        while(!line.equals("Home")){
            if(line.equals("back")) {
                if (history.size() == 1) {
                    System.out.println(history.peek());
                } else {
                    prevUrl = history.pop();
                    System.out.println(prevUrl);
                }
            } else {
                // save prev before moving to the next
                // only if prev is different from no pev message
                if(!"no previous URLs".equals(prevUrl)){
                    history.push(prevUrl);
                }
                // move to the next
                prevUrl = line;
                // print new address
                System.out.println(line);
            }
            line = scanner.nextLine();
        }
    }
}
