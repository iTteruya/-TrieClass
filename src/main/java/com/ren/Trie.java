package com.ren;

import java.util.*;

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

    public void delete(String word) {
        ArrayList<Character> letters = new ArrayList<Character>();
        for (char ch: word.toLowerCase().toCharArray()) {
            letters.add(ch);
        }
        delete(letters, root);
    }

    private boolean delete(ArrayList<Character> list, TrieNode currentNode) {
        TrieNode newNode = currentNode;
        if (list.isEmpty()) {
            if (!newNode.isAWord) {
                return false;
            }
            newNode.isAWord = false;
            return newNode.getChildren().isEmpty();
        }
        if (newNode.branch.get(list.get(0)) == null) {
            return false;
        } else newNode = currentNode.branch.get(list.get(0));
        char character = list.get(0);
        list.remove(0);

        boolean wordCanBeDeleted = delete(list, newNode) && !currentNode.isAWord;

        if (wordCanBeDeleted) {
            currentNode.branch.remove(character);
            return currentNode.branch.keySet().isEmpty();
        }
        return false;
    }

    public ArrayList<String> getByPrefix(String prefix) {
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
                if (newNode.isAWord && !newNode.getChildren().isEmpty()) {
                    list.add(newWord);
                    wordForm(newNode, newWord, list);
                } else if (!newNode.getChildren().isEmpty()) {
                    wordForm(newNode, newWord, list);
                } else list.add(newWord);
            }
            for (ArrayList<Character> array: list) {
                words.add(getStringRepresentation(array));
            }
        return words;
    }

    public void wordForm(TrieNode current, ArrayList<Character> word, ArrayList<ArrayList<Character>> list) {
        Set<Character> children = current.getChildren();
        for (Object c : children) {
            ArrayList<Character> newWord = new ArrayList<Character>(word);
            newWord.add((Character) c);
            TrieNode newNode = current.branch.get(c);
            if (newNode.isAWord && !newNode.getChildren().isEmpty()) {
                list.add(newWord);
                wordForm(newNode, newWord, list);
            } else if (!newNode.getChildren().isEmpty()) {
                wordForm(newNode, newWord, list);
            } else list.add(newWord);
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