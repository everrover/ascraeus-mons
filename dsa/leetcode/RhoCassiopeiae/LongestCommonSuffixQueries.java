package dsa.leetcode.RhoCassiopeiae;

public class LongestCommonSuffixQueries {

  /**
   * https://leetcode.com/problems/longest-common-suffix-queries/
   *
   * Reverse the strings and build a Trie, storing the best word’s index 
   * by comparing suffixes[prefixes in reversed string] of all words.
   * Traverse the reversed query, updating the maximum index from Trie.
   * If no matching suffix, return the minimum length index.
   * 
   * ❗️ How's this hard?
   * 
   * TC: O(N+M)~O(n*maxlen(word)+m*maxlen(query)) SC: O(N)~O(n*maxlen(word))
   * N: sum of length of all words, M: sum of length of all queries
   * #array #string #trie #hard
   */

  class Node {
    Node[] next = new Node[26];
    int length = Integer.MAX_VALUE;
    int idx = Integer.MAX_VALUE;
  }

  Node root = new Node();

  public void add(String s, int idx) {
    int len = s.length();
    Node curr = root;
    for (char ch : s.toCharArray()) {
      if (curr.next[ch - 'a'] == null) {
        curr.next[ch - 'a'] = new Node();
      }
      curr = curr.next[ch - 'a'];
      if (curr.length > len) {
        curr.length = len;
        curr.idx = idx;
      } else if (curr.length == len) {
        curr.idx = Math.min(curr.idx, idx);
      }
    }
  }

  public int find(String s) {
    Node curr = root;
    int maxIdx = -1;
    for (char ch : s.toCharArray()) {
      curr = curr.next[ch - 'a'];
      if (curr == null) break;
      maxIdx = curr.idx;
    }
    return maxIdx;
  }

  public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
    int minlen = Integer.MAX_VALUE, minlenIdx = 0;
    for (int i = 0; i < wordsContainer.length; i++) {
      StringBuilder sb = new StringBuilder(wordsContainer[i]);
      add(sb.reverse().toString(), i);
      if (minlen > wordsContainer[i].length()) {
        minlen = wordsContainer[i].length();
        minlenIdx = i;
      }
    }

    int result[] = new int[wordsQuery.length];
    for (int i = 0; i < wordsQuery.length; i++) {
      StringBuilder sb = new StringBuilder(wordsQuery[i]);
      result[i] = find(sb.reverse().toString());
      if (result[i] == -1) result[i] = minlenIdx;
    }
    return result;
  }
}