import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class MyTrieSet implements TrieSet61B {

    private Node root;

    public MyTrieSet() {
        root = new Node(null);
    }

    @Override
    public void clear() {
        root = new Node(null);
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node pointer = root;
        for(int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);
            if (!pointer.getChildren().containsKey(ch)) {
                return false;
            } else {
                pointer = pointer.getChildren().get(ch);
            }
        }
        return pointer.isKey();
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node node = root;
        Node parent = node;
        for(int i = 0; i < key.length(); i++) {
            Character ch = key.charAt(i);
            node = node.getChildren().get(ch);
            if (node == null) {
                node = new Node(ch, i == key.length() - 1);
            } else {
                if (i + 1 == key.length()) {
                    node.setKey(true);
                }
            }
            if (!parent.getChildren().containsKey(ch)) {
                parent.getChildren().put(ch, node);
            }
            parent = node;
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node node = root;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> keys = new ArrayList<>();
        Queue<Node> word = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();

        for(int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            node = node.getChildren().get(ch);
            if (node == null) {
                return keys;
            }
        }
        stringBuilder.append(prefix);
        queue.addAll(node.getChildren().values());
        while (queue.size() > 0) {
            node = queue.poll();
            word.offer(node);
            if (node.isKey()) {
                while (word.size() > 0) {
                    stringBuilder.append(word.poll().getCh());
                }
                keys.add(stringBuilder.toString());
                stringBuilder.delete(2, stringBuilder.length());
                if (!node.getChildren().isEmpty()) {
                    word.add(node);
                }
            }
            queue.addAll(node.getChildren().values());
        }
        return keys;
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }

    @Getter @Setter
    private static class Node implements Comparable<Node> {
        Character ch;
        boolean isKey;
        Map<Character, Node> children;

        public Node(Character ch) {
            this.ch = ch;
            this.children = new HashMap<>();
        }

        public Node(Character ch, boolean isKey) {
            this.ch = ch;
            this.isKey = isKey;
            this.children = new HashMap<>();
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(getCh(), o.getCh());
        }
    }
}