package dsa.leetcode.mithrim_montez;

import java.util.*;

public class MaximumAmountOfMoneyRobotCanEarn {

  /**
   * https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn/description/
   *
   * Use Dynamic Programming to track the maximum coins that can be earned while
   * traversing from top-left to bottom-right of the grid. Use a 3D DP array where
   * the third dimension accounts for the number of robbers neutralized.
   *
   * TC: O(m * n * 3) SC: O(m * n * 3)
   * #dp #grid-traversal #medium
   */
  
  private static final int M = -(int) 1e9;
  private int m = 0, n = 0;

  public int maximumAmount(int[][] coins) {
    m = coins.length;
    n = coins[0].length;
    int[][][] dp = new int[m][n][3];
    for (int[][] dd : dp) {
      for (int[] d : dd) {
        Arrays.fill(d, M);
      }
    }
    dp[0][0][2] = coins[0][0];
    dp[0][0][1] = dp[0][0][0] = Math.max(0, coins[0][0]);
    return dfs(m - 1, n - 1, 0, coins, dp);
  }

  private int dfs(int idx, int jdx, int neu, int[][] coins, int[][][] dp) {
    if (dp[idx][jdx][neu] != M) return dp[idx][jdx][neu];
    int res = M;
    if (idx > 0) res = Math.max(res, coins[idx][jdx] + dfs(idx - 1, jdx, neu, coins, dp));
    if (jdx > 0) res = Math.max(res, coins[idx][jdx] + dfs(idx, jdx - 1, neu, coins, dp));
    if (coins[idx][jdx] < 0 && neu < 2) {
      if (idx > 0) res = Math.max(res, dfs(idx - 1, jdx, neu + 1, coins, dp));
      if (jdx > 0) res = Math.max(res, dfs(idx, jdx - 1, neu + 1, coins, dp));
    }
    return dp[idx][jdx][neu] = res;
  }
}