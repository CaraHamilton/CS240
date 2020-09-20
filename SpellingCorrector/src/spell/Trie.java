package spell;

//import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Set;

public class Trie implements ITrie{

    private Node root;
    private int nodeCount;
    private int wordCount;
    private int hashVal;
    private Set<String> existingWords;

    public Trie() {
        root = new Node();
        nodeCount = 1;
        wordCount = 0;
        hashVal = 0;
        existingWords = new HashSet<String>();
    }

    @Override
    public void add(String word) {
        Node n = root;
        int curLetter;
        hashVal += word.hashCode();
        for (int i = 0; i < word.length(); i++) {
            curLetter = (word.charAt(i) - 'a');
            if (n.getChildren()[curLetter] == null) {
                n.getChildren()[curLetter] = new Node();
                n = n.children[curLetter];
                nodeCount ++;
            }
            else {
                n = n.children[curLetter];
            }
            if (i == (word.length() - 1)) {
                n.incrementValue();
                if (!existingWords.contains(word)) {
                    wordCount++;
                    existingWords.add(word);
                }
            }
        }
    }

    @Override
    public Node find(String word) {
        Node n = root;
        int curLetter;
        for (int i = 0; i < word.length(); i++) {
            curLetter = (word.charAt(i) - 'a');
            if (n.getChildren()[curLetter] != null) {
                n = n.children[curLetter];
            }
            else {
                return null;
            }
            if (i == word.length() - 1) {
                if (n.getValue() > 0) {
                    return n;
                }
            }
        }
        return null;
    }

    @Override
    public int getWordCount() {

        return wordCount;
    }

    @Override
    public int getNodeCount() {

        return nodeCount;
    }

    @Override
    public String toString() {

        StringBuilder curWord = new StringBuilder();
        StringBuilder output = new StringBuilder();
        toString_Helper(root, curWord, output);

        return output.toString();
    }

    private void toString_Helper(Node n, StringBuilder curWord, StringBuilder output) {
        //check if we want to append node
        if (n.getValue() > 0) {
            output.append(curWord.toString());
            output.append("\n");
            // append n's word to the output
        }
        for (int i = 0; i < n.getChildren().length; ++i) {
            Node child = n.getChildren()[i];
            if (child != null) {
                char childLetter = (char)('a' + i);
                curWord.append(childLetter);
                toString_Helper(child, curWord, output);
                curWord.deleteCharAt(curWord.length() - 1);
            }
        }
    }

    @Override
    public int hashCode() {
        return (hashVal * wordCount);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) { //check for null
            return false;
        }
        if (this.getClass() != o.getClass()) { //check for this
            return false;
        }

        //check for class

        Trie other = (Trie) o;

        if (wordCount != other.getWordCount()) {//check nodeCount and wordCount, see if equal
            return false;
        }
        if (nodeCount != other.getNodeCount()) {
            return false;
        }
        return equals_Helper(root, other.root);

    }

    private boolean equals_Helper(Node n1, Node n2) {
        boolean isEqual = true;

        if(n1 == null && n2 == null) return true;
        if(n1 == null && n2 != null) return false;
        if(n1 != null && n2 == null) return false;
        if (n1.getValue() != n2.getValue()) { //compare counts in two nodes
            return false;
        }

        for (int i = 0; i < 26; i++) { //check to see if they have children in the same positions

            isEqual = equals_Helper(n1.children[i], n2.children[i]);
            if (isEqual == false) {
                return isEqual;
            }
        }
        return true;
    }
}
