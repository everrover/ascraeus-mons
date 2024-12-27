package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class CherryPickup {

  /**
   * https://leetcode.com/problems/cherry-pickup/
   *
   * To maximize cherry collection, we simulate the movement from (0,0) to (n-1,n-1) and back using dynamic programming with memoization. The key is to keep track of two paths moving as a pair and utilize the grid's state for optimal cherry picking, avoiding thorns. This problem is solved using a 3D dynamic programming approach to store states.
   *
   * TC: O(n^3) SC: O(n^3)
   * #array #dynamic-programming #matrix #hard
   */

  class Solution {
    private int N, M;
    private static final int MIN = Integer.MIN_VALUE;
    private static final int MIN2 = -1000000;

    public int cherryPickup(int[][] grid) {
      N = grid.length; M = grid[0].length;
      int [][][]dp = new int[N][M][M];
      for(int [][]dd: dp) for(int []d: dd) Arrays.fill(d, MIN);
      return Math.max(0, dfs(0,0,0,grid,dp));
    }

    private int dfs(int r1, int c1, int c2, final int[][]grid, final int [][][]dp){
      // we can either go right or down / left or bottom,
      // if we know three other one can be computed
      int r2 = r1+c1-c2;
      // if we've reached the end or we've reached a wall
      if(r1 == N || r2 == N || c1 == N || c2 == N || grid[r1][c1] == -1 || grid[r2][c2] == -1) return MIN2;
      else if(r1 == N-1 && c1 == M-1) return grid[r1][c1];
      else if(dp[r1][c1][c2] != MIN) return dp[r1][c1][c2];
      int res = grid[r1][c1];
      if(c1 != c2) res += grid[r2][c2]; // cherry would already be picked up
      res += Math.max(
        Math.max(
          dfs(r1, c1+1, c2+1, grid, dp),
          dfs(r1+1, c1, c2+1, grid, dp)
        ),
        Math.max(
          dfs(r1, c1+1, c2, grid, dp),
          dfs(r1+1, c1, c2, grid, dp)
        )
      );
      return dp[r1][c1][c2] = res;
    }
  }
}