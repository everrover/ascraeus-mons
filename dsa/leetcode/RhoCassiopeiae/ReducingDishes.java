package dsa.leetcode.RhoCassiopeiae;

import java.util.Arrays;

public class ReducingDishes {

  /**
   * https://leetcode.com/problems/reducing-dishes/
   * 
   * Sort satisfaction levels. Use dynamic programming to find the optimal solution.
   * Save previous best like-time coefficient and corresponding element sum in dp array.
   * If adding current element to previous best increases it, then add it.
   * 
   * TC: O(n^2) SC: O(n^2)
   * #array #dynamic-programming #greedy #sorting #hard
   */

  int[][] dp;

  private int statedfs(int idx, int kdx, int[] sat) {
    if (idx >= sat.length) return 0;
    if (dp[idx][kdx] != -1) return dp[idx][kdx];
    int res = Math.max(
      kdx * sat[idx] + statedfs(idx + 1, kdx + 1, sat),
      statedfs(idx + 1, kdx, sat)
    );
    return dp[idx][kdx] = res;
  }

  public int maxSatisfaction(int[] satisfaction) {
    Arrays.sort(satisfaction);
    dp = new int[satisfaction.length][satisfaction.length + 1];
    for (int[] d : dp) Arrays.fill(d, -1);
    return statedfs(0, 1, satisfaction);
  }
}