package spell;

public class Node implements INode{

    Node[] children = new Node[26];
    private int nodeVal;

    public Node() {
        nodeVal = 0;
    }
    @Override
    public int getValue() {
        return nodeVal;
    }

    @Override
    public void incrementValue() {
        nodeVal++;
    }

    @Override
    public Node[] getChildren() {
        return children;
    }
}
