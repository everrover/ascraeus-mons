package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class Solution {

  /**
   * https://leetcode.com/problems/longest-repeating-character-replacement/
   * 
   * Use a sliding window approach to keep track of counts of characters within window.
   * Adjust window size based on number of replacements allowed (k).
   * The longest window size gives the result.
   * 
   * TC: O(n) SC: O(1)
   * #hash-table #string #sliding-window #medium
   */

  public int characterReplacement(String s, int k) {
    final int N = s.length();
    int []cnts = new int[26];
    int l = 0, r = 0, res = 0, prevcnt = 0;
    while(r < N){
      int ch = s.charAt(r) - 'A';
      cnts[ch]++;
      prevcnt = Math.max(cnts[ch], prevcnt);
      while(r - l + 1 - prevcnt > k) {
        cnts[s.charAt(l) - 'A']--;
        l++;
      }
      res = Math.max(res, r - l + 1);
      r++;
    }
    return res;
  }
}