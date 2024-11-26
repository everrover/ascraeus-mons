package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

  /**
   * https://leetcode.com/problems/longest-repeating-character-replacement/
   * 
   * Use a sliding window approach to keep track of counts of characters within window.
   * Adjust window size based on number of replacements allowed (k).
   * The longest window size gives the result.
   * 
   * Earlier, for each character I thought of applying sliding window approach, 
   * `r - l + 1 - cnt > k` where cnt is the count of the selected character. Works in 
   * O(M*N) time complexity. M = unique characters in string
   * 
   * for each character,
   * while(r < N){
   *   if s[r] == ch: cnt++;
   *   while(r - l + 1 - cnt > k) {
   *     if s[l] == ch: cnt--;
   *     l++;
   *   }
   *   res = max(res, r - l + 1);
   *   r++;
   * }
   * 
   * 
   * But in all cases I saw the same condition works when we keep track of the 
   * prevcnt = maximum(count of a selected character) in the window. Basically window size changes
   * only when the (count of a selected character) is greater than the current window size.
   *  
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