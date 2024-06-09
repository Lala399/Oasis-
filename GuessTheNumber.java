import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Settings
        int range = 100;
        int maxAttempts = 10;
        int rounds = 3;

        int score = 0;
        
        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("You have " + maxAttempts + " attempts to guess a number between 1 and " + range + " in each round.");
        System.out.println("Let's start!");
        
        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = random.nextInt(range) + 1;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;
            
            System.out.println("\nRound " + round);
            
            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You've guessed the number.");
                    hasGuessedCorrectly = true;
                    int points = maxAttempts - attempts + 1;
                    score += points;
                    System.out.println("You scored " + points + " points.");
                }
            }
            
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The number was " + numberToGuess + ".");
            }
        }
        
        System.out.println("\nGame over! Your total score is: " + score);
        scanner.close();
    }
}
