package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class Solution {
  /**
   * https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment/
   *
   * The solution involves a Depth-First Search (DFS) approach along with dynamic programming
   * to keep track of the length of the diagonal segments. The algorithm considers both the current
   * direction and checks for a possible 90-degree turn.
   *
   * TC: O(n * m) SC: O(n * m)
   * #dfs #dynamic-programming #grid #hard
   */

  final private int[][] dirs = new int[][]{{1,1}, {1,-1}, {-1,-1}, {-1,1}};
  final private int[] expectations = new int[]{2,2,0};

  public int dfs(int i, int j, int dir, int pivoted, int expected, final int m, final int n, final int[][] g, final int[][][][] dp){
    if(0 > i || 0 > j || i >= m || j >= n) return 0;
    if(g[i][j] != expected) return 0;
    if(dp[i][j][dir][pivoted] != -1) return dp[i][j][dir][pivoted];
    int res = 0;
    int ni = i + dirs[dir][0], nj = j + dirs[dir][1], nex = expectations[expected];
    res = 1 + dfs(ni, nj, dir, pivoted, nex, m, n, g, dp);

    // When a turn is allowed, evaluate additional path by making a 90-degree turn
    if(pivoted > 0) {
      int ndir = (dir + 1) % 4;
      ni = i + dirs[ndir][0]; nj = j + dirs[ndir][1]; nex = expectations[expected];
      res = Math.max(res, 1 + dfs(ni, nj, ndir, 0, nex, m, n, g, dp));
    }
    return dp[i][j][dir][pivoted] = res;
  }

  public int lenOfVDiagonal(int[][] grid) {
    final int m = grid.length, n = grid[0].length;
    int[][][][] dp = new int[m][n][4][2];
    for(int[][][] ddd: dp) for(int[][] dd: ddd)
      for(int[] d: dd) Arrays.fill(d,-1);
    int res = 0;
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        if (grid[i][j] == 1) {
          // Start the DFS traversal from any cell containing '1'
          for(int dir = 0; dir < 4; dir++) {
            res = Math.max(res, dfs(i, j, dir, 1, 2, m, n, grid, dp));
          }
        }
      }
    }
    return res;
  }
}