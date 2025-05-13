package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class TotalCharactersInStringAfterTransformationsI {

  /**
   * https://leetcode.com/problems/total-characters-in-string-after-transformations-i/description/?envType=daily-question&envId=2025-05-13
   *
   * To solve this problem, maintain a count of each character in the string and apply
   * transformations by following the given rules. In each transformation, replace each 'z' with 'ab'
   * and shift other characters to the next alphabet character. Repeat this for `t` rounds and
   * compute the mod value since the results can be very large.
   *
   * TC: O(t * n) SC: O(1)
   * #hash-table #math #string #dynamic-programming #counting #medium
   */

  public int lengthAfterTransformations(String s, int t) {
    int []cnts = new int[26];
    int[] nxt = new int[26];
    for (int round = 0; round < t; ++round) {
      nxt[0] = cnts[25];
      nxt[1] = (cnts[25] + cnts[0]) % (int)(1e9 + 7); // Replace 'z' with 'ab' and compute modulo
      for (int i = 2; i < 26; ++i) {
          nxt[i] = cnts[i - 1];
      }
      for(char ch: s.toCharArray()) cnts[ch-'a']++;
      Arrays.fill(nxt, 0); // Prepare for the next transformation
      int []tmp = cnts;
      cnts = nxt;
      nxt = tmp;
    }
  }
}