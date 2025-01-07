package dsa.leetcode.mithrim_montez;

import java.util.*;

public class StringMatchingInAnArray {
  /**
   * https://leetcode.com/problems/string-matching-in-an-array/description/?envType=daily-question&envId=2025-01-07
   *
   * Simply iterate each word and check if it is a substring of any other word in the list.
   * Add it to the result list if it is found as a substring.
   * 
   * Ideally, i might've used a trie to store all the words and then check if a word is a substring of any other word.
   * Even in case of brute-force i might've considered KMP or Rabin-Karp algorithms.
   * 
   * TC: O(n^2 * m) SC: O(n)
   * #array #string #string-matching #easy
   */
  public List<String> stringMatching(String[] words) {
    int n = words.length;
    List<String> ans = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (i != j && words[j].contains(words[i])) {
          ans.add(words[i]);
          break;
        }
      }
    }
    return ans;
  }
}