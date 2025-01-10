package dsa.leetcode.mithrim_montez;

import java.util.*;

public class WordSubsets {

  /**
   * https://leetcode.com/problems/word-subsets/description/?envType=daily-question&envId=2025-01-10
   *
   * Determine the maximum frequency of each character required by any word in words2.
   * For each word in words1, check if it contains these maximum required frequencies for every character.
   * If it does, add it to the result list.
   *
   * TC: O(n * m + b) SC: O(1)
   * #array #hash-table #string #medium
   */

  public List<String> findWordSubsets(String[] words1, String[] words2) {
    int[] maxFreq = new int[26];
    for (String word : words2) {
      int[] freq = new int[26];
      for (char c : word.toCharArray()) {
        freq[c - 'a']++;
      }
      for (int i = 0; i < 26; i++) {
        maxFreq[i] = Math.max(maxFreq[i], freq[i]);
      }
    }

    List<String> result = new LinkedList<>();
    for (String word : words1) {
      int[] freq = new int[26];
      for (char c : word.toCharArray()) {
        freq[c - 'a']++;
      }

      boolean isUniversal = true;
      for (int i = 0; i < 26; i++) {
        if (freq[i] < maxFreq[i]) {
          isUniversal = false;
          break;
        }
      }

      if (isUniversal) {
        result.add(word);
      }
    }

    return result;
  }
}