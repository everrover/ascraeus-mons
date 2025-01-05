package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class FindMirrorScoreOfAString {
  
  /**
   * https://leetcode.com/problems/find-mirror-score-of-a-string/description/
   *
   * To find the mirror score, iterate through the string and use a map to track indices of characters and their mirrored counterparts.
   * - Calculate mirror index using the formula `25 - (character ASCII - a ASCII)`.
   * - Find unmarked closest mirrored pair and sum their distance to score.
   *
   * TC: O(n) SC: O(n)
   * #two-pointers #hash-map #mirror #medium
   */
  
  class Solution {
    public long calculateScore(String s) {
      long res = 0L;
      List<Integer>[] map = new List[26];
      for (int i = 0; i < 26; i++) map[i] = new ArrayList<>();
      for (int i = 0; i < s.length(); i++) {
        int ch = s.charAt(i) - 'a';
        int mirror = 25 - ch;
        if (map[mirror].isEmpty()) {
          map[ch].add(i);
        } else {
          // Add the difference to the result and remove the last index
          res += i - map[mirror].get(map[mirror].size() - 1);
          map[mirror].remove(map[mirror].size() - 1);
        }
      }
      return res;
    }
  }
}