package com.ren;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;


public class Trie {

    static class TrieNode {
        Map<Character, TrieNode> branch = new TreeMap<Character, TrieNode>();
        boolean isAWord;

        public Set<Character> getChildren() {
            return this.branch.keySet();
        }
    }

    TrieNode root = new TrieNode();

    public boolean containsWord(String word) {
        return this.find(word);
    }

    public void put(String word) {
        TrieNode currentNode = root;
        for (char character : word.toLowerCase().toCharArray()) {
            if (!currentNode.branch.containsKey(character)) {
                currentNode.branch.put(character, new TrieNode());
            }
            currentNode = currentNode.branch.get(character);
        }
        currentNode.isAWord = true;
    }

    public boolean find(String word) {
        TrieNode currentNode = root;
        for (char character : word.toLowerCase().toCharArray()) {
            if (!currentNode.branch.containsKey(character)) {
                return false;
            } else currentNode = currentNode.branch.get(character);
        }
        return currentNode.isAWord;
    }

    public void delete(String word) { //переделать
        if (this.containsWord(word)) {
            TrieNode current = root;
            ArrayList<Character> w = new ArrayList<Character>();
            for (char ch : word.toLowerCase().toCharArray()) {
                w.add(ch);
                if (this.getByPrefix(getStringRepresentation(w)).size() <= 1) {
                    current.branch.remove(ch);
                    break;
                }
                current = current.branch.get(ch);
            }
        }
    }

    public ArrayList<String> getByPrefix(String prefix) { //переделать
        ArrayList<String> words = new ArrayList<String>();
            ArrayList<ArrayList<Character>> list = new ArrayList<ArrayList<Character>>();
            TrieNode currentNode = root;
            for (char character : prefix.toLowerCase().toCharArray()) {
                currentNode = currentNode.branch.get(character);
            }
            ArrayList<Character> word = new ArrayList<Character>();
            for (char character: prefix.toCharArray()) {
                word.add(character);
            }
            for (char c: currentNode.getChildren()) {
                TrieNode newNode = currentNode.branch.get(c);
                ArrayList<Character> newWord = new ArrayList<Character>(word);
                newWord.add(c);
                String newPrefix = getStringRepresentation(newWord);
                if (!newNode.getChildren().isEmpty()) {
                    wordForm(newNode, newPrefix, list);
                } else list.add(newWord);
            }
            for (ArrayList<Character> array: list) {
                words.add(getStringRepresentation(array));
            }
        return words;
    }

    public void wordForm(TrieNode current, String word, ArrayList<ArrayList<Character>> list) {
        Set<Character> children = current.getChildren();
        for (Object c : children) {
            String newWord = word;
            ArrayList<Character> w = new ArrayList<Character>();
            for (char character : newWord.toCharArray()) {
                w.add(character);
            }
            w.add((Character) c);
            newWord = getStringRepresentation(w);
            TrieNode newNode = current.branch.get(c);
            if (newNode.isAWord && !newNode.getChildren().isEmpty()) {
                list.add(w);
                wordForm(newNode, newWord, list);
            } else if (!newNode.getChildren().isEmpty()) {
                wordForm(newNode, newWord, list);
            } else list.add(w);
        }
    }

    String getStringRepresentation(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }

}