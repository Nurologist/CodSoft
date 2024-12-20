import java.util.Random;
import java.util.Scanner;

public class Numbergame {

    public static void main(String[] args) {
        int min = 1;
        int max = 100;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalRounds = 0;
        int roundsWon = 0;

        while (playAgain) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            int guess = 0;
            int attempts = 0;
            int maxAttempts = 3; 
            System.out.println("Guess a number between " + min + " and " + max + ":");
            while (guess != randomNumber && attempts < maxAttempts) {
                guess = scanner.nextInt(); 
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("You win!");
                } else if (guess < randomNumber) {
                    System.out.println("The guess was low. Try again:");
                } else {
                    System.out.println("The guess was high. Try again:");
                }
            }

            if (guess != randomNumber) {
                System.out.println("You've used all your attempts! The number was " + randomNumber);
            } else {
                roundsWon++;
            }

            totalRounds++;
            System.out.println("Your score: " + roundsWon + "/" + totalRounds);
            System.out.println("Do you want to play again? (yes/no)");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Your final score: " + roundsWon + "/" + totalRounds);
        scanner.close();
    }
}