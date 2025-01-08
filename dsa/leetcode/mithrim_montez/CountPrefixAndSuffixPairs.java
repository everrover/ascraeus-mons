package dsa.leetcode.mithrim_montez;

public class Solution {

  /**
   * https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/description/?envType=daily-question&envId=2025-01-08
   *
   * Iterate through all index pairs (i, j), such that i < j, and check
   * if a word is both prefix and suffix of another. Count such valid pairs.
   *
   * TC: O(n * m) SC: O(1), where n is number of words and m is word length
   * #array #string #trie #rolling-hash #easy
   */

  public int countPrefixSuffixPairs(String[] words) {
    int res = 0;
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (isPS(words[i], words[j])) res++;
      }
    }
    return res;
  }

  private boolean isPS(String a, String b) {
    return a.length() <= b.length() &&
           b.substring(0, a.length()).equals(a) &&
           b.substring(b.length() - a.length(), b.length()).equals(a);
  }
}