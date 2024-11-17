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

        System.out.println("root = " + curNode);
        System.out.println("pos = " + pos);
        System.out.println("word = " + word);

        if (pos == word.length() - 1) {
            curNode.setWord(word);
            curNode.setFrequency(frequency);
            return curNode;
        }

        char ch = word.charAt(pos);
        TrieNode leafNode;
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
        System.out.println("curNode = " + curNode);
        System.out.println("leafNode = " + leafNode);
        List<TrieNode> top10 = curNode.getTop10();
        System.out.println("top10 = " + top10);
        if (top10 == null) {
            top10 = new ArrayList<>();
            top10.add(leafNode);
        } else if (top10.size() < 10) {
            top10.add(leafNode);
        } else {
            TrieNode minFrequencyNode = top10.get(0);
            for (TrieNode topNode : top10) {
                if (minFrequencyNode.getFrequency() > topNode.getFrequency()) {
                    minFrequencyNode = topNode;
                }
            }
            System.out.println("minFrequencyNode.getFrequency() = " + minFrequencyNode.getFrequency());
            if (minFrequencyNode.getFrequency() < leafNode.getFrequency()) {
                top10.remove(minFrequencyNode);
                top10.add(leafNode);
            }
        }
        System.out.println("setTop10 = " + top10);
        curNode.setTop10(top10);
    }

    public List<String> words(String prefix) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        TrieSolution trieSolution = new TrieSolution();

        trieSolution.insert("helloa", 2);
        trieSolution.insert("hellob", 3);
//        trieSolution.insert("helloc", 4);
//        trieSolution.insert("hellob", 5);
//        trieSolution.insert("helloc", 6);
//        trieSolution.insert("hellod", 7);
//        trieSolution.insert("helloe", 10);

//        List<TrieNode> top10 = trieSolution.root.getChildren().get('h').getTop10();
//        for (TrieNode topNode : top10) {
//            System.out.println("topNode.getWord() = " + topNode.getWord());
//            System.out.println("topNode.getFrequency() = " + topNode.getFrequency());
//        }
    }
}
