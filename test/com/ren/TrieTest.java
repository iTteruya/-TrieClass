package com.ren;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class TrieTest {

    @Test
    public void containsWord() {
        Trie trie = new Trie();
        trie.put("Help");
        trie.put("Helping");
        trie.put("Phantasm");
        trie.put("Hello");

        Assert.assertTrue(trie.containsWord("Help"));
        Assert.assertTrue(trie.containsWord("Helping"));
        Assert.assertTrue(trie.containsWord("Phantasm"));
        Assert.assertTrue(trie.containsWord("Hello"));

        Assert.assertFalse(trie.containsWord("Hell"));
        Assert.assertFalse(trie.containsWord("Helper"));
        Assert.assertFalse(trie.containsWord("Mystery"));
    }

    @Test
    public void wordIsFound() {
        Trie trie = new Trie();
        trie.put("Help");
        trie.put("Phantasm");
        trie.put("Hello");

        Assert.assertTrue(trie.find("Help"));
        Assert.assertTrue(trie.find("Phantasm"));
        Assert.assertTrue(trie.find("Hello"));

        Assert.assertFalse(trie.find("Hell"));
        Assert.assertFalse(trie.find("Helping"));
        Assert.assertFalse(trie.find("Mystery"));
    }

    @Test
    public void wordIsDeleted() {
        Trie trie = new Trie();
        trie.put("Programming");
        trie.put("Programmer");
        trie.put("Saber");

        Assert.assertTrue(trie.containsWord("Programming"));

        trie.delete("Programming");

        Assert.assertFalse(trie.containsWord("Programming"));
    }

    @Test
    void wordsWithThisPrefix() {
        Trie trie = new Trie();
        trie.put("Saber");
        trie.put("Saver");
        trie.put("Savour");
        trie.put("Say");
        trie.put("Saved");
        trie.put("Archer");


        ArrayList<String> answer = new ArrayList();

        answer.add("saber");
        answer.add("saved");
        answer.add("saver");
        answer.add("savour");
        answer.add("say");

        Assert.assertEquals(answer, trie.getByPrefix("sa"));
        Assert.assertNotEquals(answer, trie.getByPrefix("ar"));
    }
}