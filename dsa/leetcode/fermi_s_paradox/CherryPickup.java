package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class CherryPickup {

  /**
   * https://leetcode.com/problems/cherry-pickup/
   *
   * To maximize cherry collection, we simulate the movement from (0,0) to (n-1,n-1) and 
   * back considering two people going the same way but possibly on different paths.
   * 
   * r1 + c1 = r2 + c2, since both would cover the same distance.
   *
   * TC: O(n^3) SC: O(n^3)
   * #array #dynamic-programming #matrix #hard
   */

    private int N, M;
    private static final int MIN = Integer.MIN_VALUE;

    public int cherryPickup(int[][] grid) {
      N = grid.length; M = grid[0].length;
      int [][][]dp = new int[N][M][M];
      for(int [][]dd: dp) for(int []d: dd) Arrays.fill(d, MIN);
      return Math.max(0, dfs(0,0,0,grid,dp));
    }

    private int dfs(int r1, int c1, int c2, final int[][]grid, final int [][][]dp){
      // we can either go right or down / left or bottom,
      // if we know three other one can be computed - saves memo space and time
      int r2 = r1+c1-c2;
      // if we've reached the end or we've reached a wall
      if(r1 == N || r2 == N || c1 == N || c2 == N || grid[r1][c1] == -1 || grid[r2][c2] == -1) return MIN;
      else if(r1 == N-1 && c1 == M-1) return grid[r1][c1];
      else if(dp[r1][c1][c2] != MIN) return dp[r1][c1][c2];
      int res = grid[r1][c1];
      if(c1 != c2) res += grid[r2][c2]; // if diff cols, diff locations would be there for p1 and p2
      // else same location for both
      res += Math.max(
        Math.max( // same row diff col for p2
          dfs(r1, c1+1, c2+1, grid, dp),
          dfs(r1+1, c1, c2+1, grid, dp)
        ),
        Math.max( // same col diff row for p2
          dfs(r1, c1+1, c2, grid, dp),
          dfs(r1+1, c1, c2, grid, dp)
        )
      );
      return dp[r1][c1][c2] = res;
    }
}