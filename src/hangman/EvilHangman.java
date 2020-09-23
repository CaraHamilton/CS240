package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EvilHangman {

    public static void main(String[] args) throws IOException, GuessAlreadyMadeException {

        EvilHangmanGame beginGame = new EvilHangmanGame();
        boolean gameOver = false;
        File theFile = new File(args[0]);
        int wordLength = Integer.parseInt(args[1]);
        int numGuesses = Integer.parseInt(args[2]);
        char letterGuess;

        try {
            beginGame.startGame(theFile, wordLength);
        } catch (EmptyDictionaryException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (gameOver == false) {

            System.out.println("You have " + numGuesses + " guesses left");
            System.out.print("Used letters:");
            for (char letter:
                    beginGame.GuessedLetters) {
                System.out.print(" ");
                System.out.print(letter);
            }
            System.out.print("\n");
            System.out.println("Word: " + beginGame.partialWord.toString());
            System.out.println("Enter guess:");
            Scanner newSc = new Scanner(System.in);
            String currentGuess = newSc.nextLine();
                letterGuess = currentGuess.charAt(0);
            if (currentGuess.length() > 1) {
                System.out.println("You entered too many letters. Using " + letterGuess);
            }

            while (!(letterGuess >= 'a' && letterGuess <= 'z') && !(letterGuess >= 'A' && letterGuess <= 'Z')) { // check if valid guess

                System.out.println("Invalid input");
                System.out.println("Enter guess:");
                letterGuess = (char) System.in.read();
                System.in.skip(1);
            }

            try {
                beginGame.updatedSet = beginGame.makeGuess(letterGuess);
            } catch (GuessAlreadyMadeException e) {
                e.printStackTrace();
            }

            if (beginGame.validGuess == false) {
                System.out.println("You already guessed that letter");
            }
            else if (beginGame.correctGuess == false) {
                System.out.println("Sorry, there are no " + letterGuess + "'s");
                numGuesses--; //keep track of how many turns are left
            }
            else {
                System.out.println("Yes, there is " + beginGame.numLetters + " " + letterGuess);
            }

            if ((numGuesses == 0) || (beginGame.wonGame == true)) {
                gameOver = true;
            }

        }
        if (beginGame.wonGame == false) {
            System.out.println("You lose!");
            System.out.print("The word was: ");
            for (String word:
                    beginGame.updatedSet) {
                System.out.println(word);
                break;
            }
        }
        else {
            System.out.println("You win!");
            System.out.println("The word was: " + beginGame.partialWord.toString());
        }
    }
}
