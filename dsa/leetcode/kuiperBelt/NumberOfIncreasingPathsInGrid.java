package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class NumberOfIncreasingPathsInGrid {
  /**
   * https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/
   *
   * Define f(i, j) as the number of increasing paths starting from cell (i, j). Use DFS and memoization to avoid re-computation.
   * Traverse every cell to check as starting point and increment result modulo 10^9+7.
   *
   * TC: O(m * n) SC: O(m * n)
   * #array #dynamic-programming #depth-first-search #graph #memoization #matrix #hard
   */

  private final static int [][]MV = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
  private final static int MOD = (int)1e9 + 7;
  private int m, n;
  private int [][]g;
  private int [][]v;

  public int countPaths(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    g = grid;
    int res = 0;
    v = new int[m][n];
    for(int []x: v) Arrays.fill(x, -1);
    for(int i = 0; i < m; i++)
      for(int j = 0; j < n; j++)
        res = (res + dfs(i, j)) % MOD;
    return res;
  }

  private int dfs(int i, int j) {
    if (v[i][j] != -1) return v[i][j];
    v[i][j] = 0;
    int res = 1;
    for (int []move: MV) {
      if ((!isvalid(i + move[0], j + move[1])) || g[i][j] >= g[i + move[0]][j + move[1]]) continue;
      res = (res + dfs(i + move[0], j + move[1])) % MOD;
    }
    return v[i][j] = res;
  }

  private boolean isvalid(int i, int j) {
    return (i >= 0 && i < m && j >= 0 && j < n);
  }
}