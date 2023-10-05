package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.Demo;



import java.util.Random;
import java.util.Scanner;

public class Demo {
    public static class Message {
        public Random random = new Random();

        private String[] phrases = {
                "Excellent product.",
                "Such a great product.",
                "I always use that product.",
                "Best product of its category.",
                "Exceptional product.",
                "I can’t live without this product.",
        };

        private String[] events = {
                "Now I feel good.",
                "I have succeeded with this product.",
                "Makes miracles. I am happy of the results!",
                "I cannot believe but now I feel awesome.",
                "Try it yourself, I am very satisfied.",
                "I feel great!"
        };

        private String[] authors = {
                "Diana",
                "Petya",
                "Stella",
                "Elena",
                "Katya",
                "Iva",
                "Annie",
                "Eva",
        };

        private String[] cities = {
                "Burgas",
                "Sofia",
                "Plovdiv",
                "Varna",
                "Ruse"
        };

        public String generateMessage() {
            return String.format("%s %s %s - %s",
                    phrases[random.nextInt(phrases.length)],
                    events[random.nextInt(events.length)],
                    authors[random.nextInt(authors.length)],
                    cities[random.nextInt(cities.length)]);
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberMessages = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberMessages; i++) {
            Message message = new Message();
            System.out.println(message.generateMessage());
        }
    }
}
