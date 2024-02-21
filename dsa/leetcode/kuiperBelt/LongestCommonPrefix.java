package dsa.leetcode.kuiperBelt;

public class LongestCommonPrefix {

  /**
   * https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
   * 
   * This approach uses a Trie to store all the prefixes of the numbers in the two arrays. 
   * By traversing the Trie, the length of the longest common prefix is found.
   * 
   * TC: O(n * m) SC: O(n * m) where n is the number of elements in the array and m is the length of the longest number.
   * #trie #array #medium #string
   */

  public int longestCommonPrefix(int[] arr1, int[] arr2) {
    Trie trie = new Trie();
    for (int num : arr1) trie.insert(Integer.toString(num), true);
    for (int num : arr2) trie.insert(Integer.toString(num), false);
    String longestPrefix = trie.getLongestCommonPrefix();
    return longestPrefix.length();
  }

  private static class TrieNode {
    TrieNode[] children;
    boolean flag1;
    boolean flag2;

    public TrieNode(){
      this.children = new TrieNode[10];
      this.flag1 = false;
      this.flag2 = false;
    }
  }

  private static class Trie {
    private TrieNode root;
    private String longestPrefix;

    public Trie() {
      root = new TrieNode();
      longestPrefix = "";
    }

    public void insert(String word, boolean isFirstArray) {
      TrieNode current = root;
      for (char c : word.toCharArray()) {
        if (current.children[c - '0'] == null) {
          current.children[c - '0'] = new TrieNode();
        }
        current = current.children[c - '0'];
        if (isFirstArray) current.flag1 = true;
        else current.flag2 = true;
      }
    }

    public String getLongestCommonPrefix() {
      dfs(root, new StringBuilder());
      return this.longestPrefix;
    }

    private void dfs(TrieNode node, StringBuilder currentPrefix) {
      if (node == null) return;

      for (int i = 0; i < 10; i++) {
        if (node.children[i] != null && node.children[i].flag1 && node.children[i].flag2) {
          currentPrefix.append(i);
          if (currentPrefix.length() > this.longestPrefix.length()) {
            this.longestPrefix = currentPrefix.toString();
          }
          dfs(node.children[i], currentPrefix);
          currentPrefix.deleteCharAt(currentPrefix.length() - 1);
        }
      }
    }
  }
}