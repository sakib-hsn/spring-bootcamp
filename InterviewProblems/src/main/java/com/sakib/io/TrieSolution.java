package com.sakib.io;

import java.util.ArrayList;
import java.util.List;

public class TrieSolution {

    private TrieNode root;

    public TrieSolution() {
        root = new TrieNode();
    }

    public void insert(String word, int frequency) {
        insertRecursive(root, 0, word, frequency);
    }

    private TrieNode insertRecursive(TrieNode curNode, int pos, String word, int frequency) {

//        System.out.println("root = " + curNode);
//        System.out.println("pos = " + pos);
//        System.out.println("word = " + word);

        if (pos == word.length()) {
            curNode.setWord(word);
            curNode.setFrequency(frequency);
            return curNode;
        }

        char ch = word.charAt(pos);
        TrieNode leafNode = null;
        if (curNode.getChildren().containsKey(ch)) {
            leafNode = insertRecursive(curNode.getChildren().get(ch), pos + 1, word, frequency);
        } else {
            TrieNode child = new TrieNode();
            curNode.getChildren().put(ch, child);
            leafNode = insertRecursive(child, pos + 1, word, frequency);
        }

        insertTop10(curNode, leafNode);

        return leafNode;
    }

    private void insertTop10(TrieNode curNode, TrieNode leafNode) {
        if (curNode.getTop10() == null || curNode.getTop10().size() < 10) {
            curNode.addTop10Node(leafNode);
        } else {
            TrieNode minFrequencyNode = curNode.getTop10().get(0);
            for (TrieNode topNode : curNode.getTop10()) {
                if (minFrequencyNode.getFrequency() > topNode.getFrequency()) {
                    minFrequencyNode = topNode;
                }
            }
            if (minFrequencyNode.getFrequency() < leafNode.getFrequency()) {
                curNode.removeTop10Node(minFrequencyNode);
                curNode.addTop10Node(leafNode);
            }
        }
    }

    public List<String> words(String prefix) {
        TrieNode node = searchPrefix(root, prefix, 0);
        if (node == null) {
            return new ArrayList<>();
        }

        List<String> top10Words = new ArrayList<>();
        for (TrieNode topNode : node.getTop10()) {
            top10Words.add(topNode.getWord());
        }
        return top10Words;
    }

    private TrieNode searchPrefix(TrieNode root, String prefix, int pos) {
        if (root == null) {
            return null;
        }
        if (pos == prefix.length()) {
            return root;
        }
        char ch = prefix.charAt(pos);
        return searchPrefix(root.getChildren().get(ch), prefix, pos + 1);
    }

    public static void main(String[] args) {
        TrieSolution trieSolution = new TrieSolution();

        trieSolution.insert("hella", 2);
        trieSolution.insert("hellb", 3);
        trieSolution.insert("hellc", 4);
        trieSolution.insert("helld", 5);
        trieSolution.insert("helle", 6);
        trieSolution.insert("hellf", 7);
        trieSolution.insert("hellg", 10);
        
        trieSolution.insert("hellh", 1); // it will remove
        
        trieSolution.insert("helli", 8);
        trieSolution.insert("hellj", 9);
        trieSolution.insert("hellk", 50);

        List<String> top10Words = trieSolution.words("hell");
        for (String word : top10Words) {
            System.out.println("word = " + word);
        }
    }
}
