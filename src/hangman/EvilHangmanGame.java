package hangman;

import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map;


public class EvilHangmanGame implements IEvilHangmanGame{

    Set<String> theDictionary = new TreeSet<String>();
    Set<String> updatedSet = theDictionary;
    SortedSet<Character> GuessedLetters = new TreeSet<Character>();
    StringBuilder partialWord = new StringBuilder();
    boolean correctGuess = false;
    boolean wonGame = false;
    boolean validGuess = true;
    int numLetters;

    public EvilHangmanGame() {

    }

    @Override
    public void startGame(File dictionary, int wordLength) throws IOException, EmptyDictionaryException {
        theDictionary = new TreeSet<String>();
        if (!(dictionary.exists())) {
            throw new IOException();
        }
        Scanner sc = new Scanner(dictionary);
        while (sc.hasNext()) {
          String word = sc.next();
          if (word.length() == wordLength) {
              theDictionary.add(word);
          }
        }
        if (theDictionary.isEmpty()) {
          throw new EmptyDictionaryException();
        }
        for (int i = 0; i < wordLength; i++) { //initialize the partial word with all dashes based on the word length
            partialWord.append('-');
        }
        updatedSet = theDictionary;
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        validGuess = true;
        Set<String> curSet = updatedSet;
        String curKey = null;
        numLetters = 0;
        correctGuess = false;
        Map<String, Set<String>> partition = new TreeMap<String, Set<String>>();

        guess = Character.toLowerCase(guess); // make all guesses lowercase

        if (GuessedLetters.contains(guess)) {
            validGuess = false;
            throw new GuessAlreadyMadeException();
        }

        GuessedLetters.add(guess);

        for (String word: //start partitioning
             updatedSet) {
            String stringKey = makeKey(guess, word);
            if (partition.get(stringKey) != null) {
                partition.get(stringKey).add(word);
            }
            else {
                Set<String> keySet = new TreeSet<String>();
                keySet.add(word);
                partition.put(stringKey, keySet);
            }
        }

        Iterator<Map.Entry<String, Set<String>>> itr = partition.entrySet().iterator(); // begin finding best set in map
        int max = 0;
        while(itr.hasNext()) {
            Map.Entry<String, Set<String>> entry = itr.next();
            if (entry.getValue().size() >= max) {
                String bestKey;
                if (entry.getValue().size() == max) {
                    bestKey = tieBreaker(entry.getKey(), curKey, guess);
                }
                else {
                    bestKey = entry.getKey();
                }
                if (bestKey.equals(entry.getKey())) { //only if new set won the tie breaker or is bigger, update
                    max = entry.getValue().size();
                    curSet = entry.getValue(); //update new dictionary(set of words)
                    curKey = entry.getKey(); //update to keep track of current key
                }
            }
        }
        for (int i = 0; i < curKey.length(); i++) { //update the partial word shown to user and if correct guess
            if (curKey.charAt(i) != '-') {
                numLetters++;
                correctGuess = true;
                partialWord.deleteCharAt(i);
                partialWord.insert(i, guess);
            }
        }
        updatedSet = curSet;
        haveWon();
        return curSet;
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return GuessedLetters;
    }

    private String makeKey(char theGuess, String theWord) {
        StringBuilder key = new StringBuilder();
        if (updatedSet.size() == 1) {

        }
        for (int i = 0; i < theWord.length(); i++) {
            if (theWord.charAt(i) == theGuess) {

                key.append(theGuess);
            }
            else {
                key.append("-");
            }
        }
        return key.toString();
    }

    private String tieBreaker(String tiedKey, String curBest, Character userGuess) {
        int curBestCount = 0;
        int tiedCount = 0;
        int curPosition = 0;
        int tiedPosition = 0;
        for (int i = 0; i < tiedKey.length(); i++) {
            if (tiedKey.charAt(i) == userGuess) {
                tiedCount++;
            }
        }
        for (int i = 0; i < curBest.length(); i++) {
            if (curBest.charAt(i) == userGuess) {
                curBestCount++;
            }
        }
        if (tiedCount < curBestCount) {
            System.out.println("best " + tiedKey);
            return tiedKey;
        }
        else if (tiedCount == curBestCount){
            for (int i = 0; i < tiedKey.length(); i++) {
                if (tiedKey.charAt(i) == userGuess) {
                    tiedPosition = tiedPosition + i;
                }
            }
            for (int i = 0; i < curBest.length(); i++) {
                if (curBest.charAt(i) == userGuess) {
                    curPosition = curPosition + i;
                }
            }
            if (tiedPosition > curPosition) {
                return tiedKey;
            }
        }
        return curBest;
    }

    private boolean haveWon () {
        wonGame = true;
        for (int i = 0; i < partialWord.length(); i++) {
            if (partialWord.charAt(i) == '-') {
                wonGame = false;
            }
        }
        return wonGame;
    }
}

