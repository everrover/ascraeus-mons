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

  private static final int M = (int)1e9+7;
  public int lengthAfterTransformations(String s, int t) {
    int []cnts = new int[26];
    int[] nxt = new int[26];
    for(char ch: s.toCharArray()) cnts[ch-'a']++;
    for (int round = 0; round < t; ++round) {
      Arrays.fill(nxt, 0);
      nxt[0] = cnts[25];
      nxt[1] = (cnts[25] + cnts[0]) % M;
      for (int i = 2; i < 26; ++i) {
        nxt[i] = cnts[i - 1];
      }
      int []tmp = cnts;
      cnts = nxt;
      nxt = tmp;
    }
    int res = 0;
    for(int cnt: cnts) res = (res + cnt) % M;
    return res;
  }
}