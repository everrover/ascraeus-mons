package dsa.leetcode.RhoCassiopeiae;

import java.util.Arrays;

public class MinimumCostTreeFromLeafValues {
  
  /**
   * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
   *
   * Dynamic programming solution to find the minimum cost tree from leaf values.
   * The main function deploys recursive dfs calls to calculate minimum cost.
   *
   * TC: O(n^3) SC: O(n^2)
   * #array #dynamic-programming #stack #monotonic-stack #greedy #medium
   */
  
  public int dfs(int idx, int jdx, int[] arr, int[][] dp, int[][] max) {
    if (idx >= jdx) return 0;  // If indices are same or crossed, cost is 0
    if (dp[idx][jdx] != -1) {
      return dp[idx][jdx];  // Return previously calculated dp value if available
    }
    int res = Integer.MAX_VALUE;
    for (int kdx = idx; kdx < jdx; kdx++) {
      int dfsres = dfs(idx, kdx, arr, dp, max) + dfs(kdx + 1, jdx, arr, dp, max);
      res = Math.min(res, max[idx][kdx] * max[kdx + 1][jdx] + dfsres);
    }
    max[idx][jdx] = Math.max(max[idx][idx], max[idx + 1][jdx]);
    return dp[idx][jdx] = res;  // Store the computed result in dp table
  }

  public int mctFromLeafValues(int[] arr) {
    int[][] dp = new int[arr.length][arr.length];
    int[][] max = new int[arr.length][arr.length];
    for (int[] d : dp) Arrays.fill(d, -1);  // Initialize dp table with -1
    for (int i = 0; i < arr.length; i++) {
      max[i][i] = arr[i];  // Initialize maximum values
      dp[i][i] = 0;
    }
    return dfs(0, arr.length - 1, arr, dp, max);
  }
}