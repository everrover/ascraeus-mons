package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class StreamOfCharacters {
  /**
   * https://leetcode.com/problems/stream-of-characters/?envType=problem-list-v2&envId=design
   *
   * Implemented using a Trie to store the words in reverse.
   * As new characters arrive in the stream, check if any word in
   * reverse is a suffix of the stream.
   *
   * Given constraints allowed it. So did it this way.
   *
   * TC: O(W * L) for building; O(L) per query, where W is total words, L is max word length
   * SC: O(W * L)
   * #trie #design #datastructure #hard
   */

  private TrieNode root;
  private StringBuilder queryStream;

  private class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
  }

  public StreamOfCharacters(String[] words) {
    root = new TrieNode();
    queryStream = new StringBuilder();
    for (String word : words) {
      addWord(word);
    }
  }

  private void addWord(String word) {
    TrieNode node = root;
    for (int i = word.length() - 1; i >= 0; i--) {
      int index = word.charAt(i) - 'a';
      if (node.children[index] == null) {
        node.children[index] = new TrieNode();
      }
      node = node.children[index];
    }
    node.isWord = true;
  }

  public boolean query(char letter) {
    queryStream.append(letter);
    TrieNode node = root;
    for (int i = queryStream.length() - 1; i >= 0; i--) {
      int index = queryStream.charAt(i) - 'a';
      node = node.children[index];
      if (node == null) {
        return false;
      }
      if (node.isWord) {
        return true;
      }
    }
    return false;
  }
}