package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountSubmatricesWithAllOnes {

  /**
   * https://leetcode.com/problems/count-submatrices-with-all-ones/description/
   *
   * For each row i, create an array nums where:
   * if mat[i][j] == 0 then nums[j] = 0 else nums[j] = nums[j-1] + 1.
   * The number of rectangles that end at row i is calculated using these arrays.
   *
   * Expected solution is O(n^3).
   *
   * TC: O(n^3) SC: O(n^2)
   * #array #dynamic-programming #stack #matrix #monotonic-stack #medium
   */

  public int numSubmat(int[][] grid) {
    final int m = grid.length, n = grid[0].length;
    int[][] inc = new int[n][m];
    for (int i = 0; i < m; i++) inc[n - 1][i] = grid[i][n - 1] == 0 ? -1 : 1;
    int res = 0;
    for (int i = 0; i < m; i++) {
      for (int j = n - 2; j >= 0; j--) {
        if (grid[i][j] == 0) {
          inc[j][i] = 1;
        } else if (grid[i][j + 1] == 0) {
          inc[j][i] = -1;
        } else {
          inc[j][i] = inc[j + 1][i] + 1;
        }
      }
    }
    // Implement logic to calculate number of submatrices here
    // Using min(inc[j, .. idx]) approach
    return res;
  }
}