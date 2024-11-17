package com.sakib.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children = new HashMap<>();

    private String word;

    private int frequency;

    private List<TrieNode> top10;

    @Override
    public String toString() {
        return "TrieNode{" +
                "word='" + word + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<TrieNode> getTop10() {
        return top10;
    }

    public void setTop10(List<TrieNode> top10) {
        this.top10 = top10;
    }
}
