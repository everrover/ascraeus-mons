package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class BombEnemy {
  /**
   * https://leetcode.com/problems/bomb-enemy/description/?envType=weekly-question&envId=2025-05-15
   *
   * To solve the problem, traverse each cell in the grid. For each empty cell, calculate the number of
   * enemies (E) that can be killed in the same row and column before hitting a wall (W). Store results
   * using dynamic programming arrays to avoid recalculating enemy counts. Find the maximum of these counts
   * over all empty cells.
   *
   * TC: O(m * n), SC: O(m * n)
   * #array #dynamic-programming #matrix #medium
   */
  public int maxKilledEnemies(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int rows = grid.length;
    int cols = grid[0].length;
    int maxEnemies = 0;

    // DP arrays to track enemies in rows and columns
    int[][] dpx = new int[rows][cols];
    int[][] dpy = new int[rows][cols];
    for(int[] dd : dpx) Arrays.fill(dd, -1);
    for(int[] dd : dpy) Arrays.fill(dd, -1);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 'W') dpx[i][j] = dpy[i][j] = 0;

        // Calculate horizontal enemies
        if (dpx[i][j] == -1) {
          int e = 0;
          for (int k = j; k < cols && grid[i][k] != 'W'; k++) {
            if (grid[i][k] == 'E') e++;
          }
          for (int k = j; k < cols && grid[i][k] != 'W'; k++) {
            dpx[i][k] = e;
          }
        }

        // Calculate vertical enemies
        if (dpy[i][j] == -1) {
          int e = 0;
          for (int k = i; k < rows && grid[k][j] != 'W'; k++) {
            if (grid[k][j] == 'E') e++;
          }
          for (int k = i; k < rows && grid[k][j] != 'W'; k++) {
            dpy[k][j] = e;
          }
        }

        // Update max enemies killed
        if (grid[i][j] == '0') {
          maxEnemies = Math.max(maxEnemies, dpx[i][j] + dpy[i][j]);
        }
      }
    }

    return maxEnemies;
  }
}