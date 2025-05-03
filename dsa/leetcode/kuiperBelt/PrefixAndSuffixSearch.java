package dsa.leetcode.KuiperBelt;

import java.util.*;

public class PrefixAndSuffixSearch {

    /**
    * https://leetcode.com/problems/prefix-and-suffix-search/submissions/
    *
    * A Trie structure is used to insert words with prefix and suffix.
    * Insert patterns like "suffix{prefix" into the Trie.
    * To find a word with given prefix and suffix construct the same pattern and check in Trie.
    * 
    * TC: O(n * k^2) SC: O(n * k)
    * #trie #string #design #hard
    */

    class TrieNode {
        TrieNode[] children;
        int weight;

        TrieNode() {
            children = new TrieNode[27]; // Using 27 for '{' as a separator
            weight = -1;
        }
    }

    TrieNode root;

    public PrefixAndSuffixSearch(String[] words) {
        root = new TrieNode();
        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight];
            for (int i = 0; i <= word.length(); ++i) {
                String newWord = word.substring(i) + '{' + word;
                addWord(newWord, weight);
            }
        }
    }

    private void addWord(String word, int weight) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
            node.weight = weight;
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode node = root;
        String target = suffix + '{' + prefix;
        for (char ch : target.toCharArray()) {
            if (node.children[ch - 'a'] == null) return -1;
            node = node.children[ch - 'a'];
        }
        return node.weight;
    }
}