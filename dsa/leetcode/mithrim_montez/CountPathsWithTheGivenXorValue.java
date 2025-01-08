package dsa.leetcode.mithrim_montez;

import java.util.*;

public class CountPathsWithTheGivenXorValue {
  
  /**
   * https://leetcode.com/problems/count-paths-with-the-given-xor-value/
   *
   * Use DFS with memoization to explore all possible paths.
   * Calculate XOR at each step and check against k at the destination.
   * 
   * TC: O(m * n * 16) SC: O(m * n * 16)
   * #array #dynamic-programming #bit-manipulation #matrix #medium
   */
  
  private int MOD = 1000000007;

  public int countPaths(int[][] grid, int k) {
    int n = grid.length;
    int m = grid[0].length;
    int[][][] dp = new int[n][m][1 << 4];
    for (int[][] a : dp) {
      for (int[] b : a) {
        Arrays.fill(b, -1);
      }
    }
    return dfs(grid, 0, 0, k, 0, dp);
  }
  
  private int dfs(int[][] grid, int i, int j, int k, int ans, int[][][] dp) {
    if (i == grid.length - 1 && j == grid[0].length - 1 && (ans ^ grid[i][j]) == k) return 1;
    if (i >= grid.length || j >= grid[0].length) return 0;
    if (dp[i][j][ans] != -1) return dp[i][j][ans];

    int temp = ans ^ grid[i][j];
    int right = dfs(grid, i, j + 1, k, temp, dp);
    int down = dfs(grid, i + 1, j, k, temp, dp);
    return dp[i][j][ans] = (right + down) % MOD;
  }
}