package dsa.leetcode.EuropasOceanWorld;

class SetMatrixZeroes {

  /**
   * https://leetcode.com/problems/set-matrix-zeroes/description/?envType=daily-question&envId=2025-05-21
   *
   * If any cell of the matrix has a zero, record its row and column. And use it for mods.
   *
   * Also, we can use first row and first column as a marker to mark the rows and columns that need to be set to zero. How,
   * by setting it to 0, then all the row and column values need to be set to zero.
   *
   * Also, BFS/DFS can be used along the row and column of the zero cell to set all cells in that row and column to zero.
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