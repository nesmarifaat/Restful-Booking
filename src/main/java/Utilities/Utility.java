package Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.List;
import java.util.Random;


public class Utility {




//TODO: To generate random first name
    public static class NameGenerator {

        private static final List<String> firstNamesMale = List.of(
                "James", "John", "Robert", "Michael", "William",
                "David", "Richard", "Charles", "Joseph", "Thomas",
                "Christopher", "Daniel", "Paul", "Mark", "Donald",
                "George", "Kenneth", "Steven", "Edward", "Brian",
                "Ronald", "Anthony", "Kevin", "Jason", "Jeffrey");

        private static final List<String> firstNamesFemale = List.of(
                "Mary", "Patricia", "Jennifer", "Linda", "Elizabeth",
                "Barbara", "Susan", "Jessica", "Sarah", "Karen",
                "Nancy", "Lisa", "Betty", "Margaret", "Dorothy",
                "Helen", "Sandra", "Donna", "Carol", "Ruth",
                "Sharon", "Michelle", "Laura", "Cynthia", "Deborah");

        private static final Random random = new Random();

        public static String generateRandomFirstName() {
            if (random.nextBoolean()) {
                return getRandomName(firstNamesMale);
            } else {
                return getRandomName(firstNamesFemale);
            }
        }

        private static String getRandomName(List<String> names) {
            int index = random.nextInt(names.size());
            return names.get(index);
        }
    }

//TODO: Method to generate random price between 1 and 1000




    public static class RandomNumberGenerator {

        private static final Random random = new Random();

        public static int generateRandomInteger() {
            return random.nextInt(1000) + 1; // Generate a random integer between 0 (inclusive) and 1000 (exclusive), then add 1
        }

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Random Integer: " + generateRandomInteger());
            }
        }
    }
    }

