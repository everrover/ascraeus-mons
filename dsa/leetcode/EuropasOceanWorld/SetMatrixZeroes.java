package dsa.leetcode.EuropasOceanWorld;

class Solution {

  /**
   * https://leetcode.com/problems/set-matrix-zeroes/description/?envType=daily-question&envId=2025-05-21
   *
   * If any cell of the matrix has a zero, record its row and column. Use the first cell of each row/column as a flag to minimize space usage.
   * This approach effectively uses O(1) extra space.
   *
   * TC: O(m*n) SC: O(1)
   * #array #matrix #hash-table #medium
   */

  public void setZeroes(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    boolean[] x = new boolean[n];
    boolean[] y = new boolean[m];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (matrix[i][j] == 0) {
          x[j] = true;
          y[i] = true;
        }
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        if (x[j] || y[i]) {
          matrix[i][j] = 0;
        }
      }
  }
}