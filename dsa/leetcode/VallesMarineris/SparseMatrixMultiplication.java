package dsa.leetcode.VallesMarineris;

public class SparseMatrixMultiplication {

  /**
   * https://leetcode.com/problems/sparse-matrix-multiplication/description/?envType=weekly-question&envId=2025-04-29
   *
   * Multiplies two sparse matrices mat1 and mat2 and returns the resulting matrix.
   * Only non-zero values are considered in multiplication to optimize performance.
   *
   * TC: O(m * k * n) SC: O(m * n)
   * #array #hash-table #matrix #medium
   */

  public int[][] multiply(int[][] mat1, int[][] mat2) {
    int m = mat1.length;
    int k = mat1[0].length;
    int n = mat2[0].length;

    int[][] result = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < k; j++) {
        if (mat1[i][j] != 0) { // Check if the element in mat1 is non-zero
          for (int l = 0; l < n; l++) {
            if (mat2[j][l] != 0) { // Check if the element in mat2 is non-zero
              result[i][l] += mat1[i][j] * mat2[j][l];
            }
          }
        }
      }
    }

    return result;
  }
}