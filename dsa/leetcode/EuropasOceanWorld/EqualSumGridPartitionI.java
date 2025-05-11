package dsa.leetcode.EuropasOceanWorld;

public class EqualSumGridPartitionI {

  /**
   * https://leetcode.com/problems/equal-sum-grid-partition-i/
   *
   * To determine if it's possible to partition the grid such that both resulting sections have equal sums,
   * iterate over possible horizontal and vertical cuts, maintaining cumulative sums for rows and columns.
   * Verify if any cut results in equal section sums both horizontally and vertically.
   *
   * TC: O(m*n) SC: O(m+n)
   * #matrix #grid-partition #medium
   */

  public boolean canPartition(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[] r = new int[m], c = new int[n];
    // Calculate row sums
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        r[i] += grid[i][j];
      }
    }
    // Calculate column sums
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        c[i] += grid[j][i];
      }
    }
    // Calculate cumulative row sums
    for (int i = 1; i < m; i++) r[i] += r[i - 1];
    // Check for possible horizontal cuts
    for (int i = 0; i < m - 1; i++) {
      if (r[m - 1] / 2 == r[i] && r[m - 1] % 2 == 0) return true;
    }
    // Check for possible vertical cuts
    for (int i = 1; i < n; i++) c[i] += c[i - 1];
    for (int i = 0; i < n - 1; i++) {
      if (c[n - 1] / 2 == c[i] && c[n - 1] % 2 == 0) return true;
    }
    return false;
  }

}