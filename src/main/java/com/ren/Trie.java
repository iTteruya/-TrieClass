package com.ren;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Trie {

    class TrieNode {
        Map<Character, TrieNode> branch = new TreeMap();
        boolean endNode;

        public ArrayList<Character> getChildren() {
            ArrayList<Character> list = new ArrayList();
            for (Character key: this.branch.keySet()) {
                list.add(key);
            }
            return list;
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
        currentNode.endNode = true;
    }

    public boolean find(String word) {
        TrieNode currentNode = root;
        for (char character : word.toLowerCase().toCharArray()) {
            if (!currentNode.branch.containsKey(character)) {
                return false;
            } else currentNode = currentNode.branch.get(character);
        }
        return currentNode.endNode;
    }

    public boolean containsPrefix(String word) {
        TrieNode currentNode = root;
        for (char character : word.toLowerCase().toCharArray()) {
            if (!currentNode.branch.containsKey(character)) {
                return false;
            } else currentNode = currentNode.branch.get(character);
        }
        return true;
    }

    public void delete(String word) {
        if (this.containsWord(word)) {
            TrieNode current = root;
            ArrayList<Character> w = new ArrayList();
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

    public ArrayList<String> getByPrefix(String prefix) {
        ArrayList<String> words = new ArrayList();
        if (this.containsPrefix(prefix)) {
            ArrayList<ArrayList<Character>> list = new ArrayList();
            TrieNode currentNode = root;
            for (char character : prefix.toLowerCase().toCharArray()) {
                currentNode = currentNode.branch.get(character);
            }
            for (char c: currentNode.getChildren()) {
                TrieNode newNode = currentNode.branch.get(c);
                ArrayList<Character> word = new ArrayList();
                for (char character: prefix.toCharArray()) {
                    word.add(character);
                }
                word.add(c);
                String newPrefix = getStringRepresentation(word);
                if (!newNode.endNode) {
                    wordForm(newNode, newPrefix, list);
                } else list.add(word);
            }
            for (ArrayList<Character> array: list) {
                words.add(getStringRepresentation(array));
            }
        }
        return words;
    }

    public void wordForm(TrieNode current, String word, ArrayList<ArrayList<Character>> list) {
        ArrayList<Character> children = current.getChildren();
        for (char c: children) {
            String newWord = word;
            ArrayList<Character> w = new ArrayList();
            for (char character: newWord.toCharArray()) {
                w.add(character);
            }
            w.add(c);
            newWord = getStringRepresentation(w);
            TrieNode newNode = current.branch.get(c);
            if (!newNode.endNode) {
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