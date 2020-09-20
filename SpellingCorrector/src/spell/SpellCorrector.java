package spell;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

public class SpellCorrector implements ISpellCorrector{

    private Trie dictionary = new Trie();

    public SpellCorrector() {

    }

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        Scanner sc = new Scanner(new File(dictionaryFileName));
        while (sc.hasNext()) {
            String word = sc.next();
            dictionary.add(word);
        }
        sc.close();
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        Set<String> editDistanceOne = new HashSet<String>();
        Set<String> editDistanceTwo = new HashSet<String>();
        inputWord = inputWord.toLowerCase();
        if (dictionary.find(inputWord) != null) {
            return inputWord;
        }
        else {
            deletion(inputWord, editDistanceOne);
            transposition(inputWord, editDistanceOne);
            alteration(inputWord, editDistanceOne);
            insertion(inputWord, editDistanceOne);
        }
        if (wordSuggestion(editDistanceOne) == null) {
            for (String s:editDistanceOne) {
                deletion(s, editDistanceTwo);
                transposition(s, editDistanceTwo);
                alteration(s, editDistanceTwo);
                insertion(s, editDistanceTwo);
            }
            return wordSuggestion(editDistanceTwo);
        }
        else {
            return wordSuggestion(editDistanceOne);
        }
    }

    private void deletion(String word, Set<String> set) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder dWord = new StringBuilder(word);
            dWord.deleteCharAt(i);
            String finalDWord = dWord.toString();
            set.add(finalDWord);
        }
    }

    private void transposition(String word, Set<String> set) {

        for (int i = 0; i < (word.length() - 1); i++) {
            StringBuilder tWord = new StringBuilder(word);
            tWord.deleteCharAt(i);
            tWord.insert((i + 1), word.charAt(i));
            String finalTWord = tWord.toString();
            set.add(finalTWord);
        }
    }

    private void alteration(String word, Set<String> set) {

        for (int i = 0; i < word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                StringBuilder aWord = new StringBuilder(word);
                aWord.deleteCharAt(i);
                aWord.insert(i, j);
                String finalAWord = aWord.toString();
                set.add(finalAWord);
            }
        }
    }

    private void insertion(String word, Set<String> set) {

        for (int i = 0; i <= word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                StringBuilder iWord = new StringBuilder(word);
                iWord.insert(i, j);
                String finalIWord = iWord.toString();
                set.add(finalIWord);
            }
        }
    }

    private String wordSuggestion(Set<String> set) {
        String bestWord = null;
        int highFrequency = 0;
        for (String s:set) {
            Node n = dictionary.find(s);
            if (n != null) {
                if (n.getValue() > highFrequency) {
                    highFrequency = n.getValue();
                    bestWord = s;
                }
            }
        }
        return bestWord;
    }
}
