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
        trie.put("May");
        trie.put("Hello");

        Assert.assertTrue(trie.containsWord("Help"));
        Assert.assertTrue(trie.containsWord("Helping"));
        Assert.assertTrue(trie.containsWord("May"));
        Assert.assertTrue(trie.containsWord("Hello"));

        Assert.assertFalse(trie.containsWord("Heal"));
        Assert.assertFalse(trie.containsWord("Helper"));
        Assert.assertFalse(trie.containsWord("Mystery"));
    }

    @Test
    public void wordIsFound() {
        Trie trie = new Trie();
        trie.put("Sound");
        trie.put("Music");
        trie.put("Sounds");
        trie.put("Muse");

        Assert.assertTrue(trie.find("Sound"));
        Assert.assertTrue(trie.find("Music"));
        Assert.assertTrue(trie.find("Muse"));
        Assert.assertTrue(trie.find("Sounds"));

        Assert.assertFalse(trie.find("Musically"));
        Assert.assertFalse(trie.find("Museum"));
        Assert.assertFalse(trie.find("Servant"));
    }

    @Test
    public void wordIsDeleted() {
        Trie trie = new Trie();
        trie.put("Programming");
        trie.put("Program");
        trie.put("Momentary");
        trie.put("Moment");

        trie.delete("Programming");
        trie.delete("Moment");

        Assert.assertFalse(trie.find("Programming"));
        Assert.assertTrue(trie.find("Program"));
        Assert.assertFalse(trie.find("Moment"));
        Assert.assertTrue(trie.find("Momentary"));
    }

    @Test
    void wordsWithThisPrefix() {
        Trie trie = new Trie();
        trie.put("Saber");
        trie.put("Saver");
        trie.put("Savour");
        trie.put("Say");
        trie.put("Saying");
        trie.put("Saved");
        trie.put("Archer");

        ArrayList<String> answer = new ArrayList<String>();

        answer.add("saber");
        answer.add("saved");
        answer.add("saver");
        answer.add("savour");
        answer.add("say");
        answer.add("saying");

        Assert.assertEquals(answer, trie.getByPrefix("sa"));
        Assert.assertNotEquals(answer, trie.getByPrefix("ar"));
    }
}
