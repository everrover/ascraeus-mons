package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class NumberOfEquivalentDominoPairs {

  /**
   * https://leetcode.com/problems/number-of-equivalent-domino-pairs/description/?envType=daily-question&envId=2025-05-04
   *
   * For each domino j, find the number of dominoes you've already seen (dominoes i with i < j) that are equivalent.
   * Use a hashmap to keep track of the dominoes seen so far.
   *
   * TC: O(n log n) because of sorting, SC: O(1)
   * #array #hash-table #counting #easy
   */

  public int numEquivDominoPairs(int[][] dominoes) {
    int res = 0;
    for (int[] p : dominoes) if (p[0] > p[1]) {
      int tmp = p[0];
      p[0] = p[1];
      p[1] = tmp;
    }
    Arrays.sort(dominoes, (a,b) -> (b[0] == a[0] ? (b[1] - a[1]) : (b[0] - a[0])));
    for (int i = 0, j = 0; i < dominoes.length; i++) {
      res += j - i - 1;
      j = Math.max(i, j);
      while (j < dominoes.length && dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1]) {
        j++;
      }
    }
    return res;
  }
}