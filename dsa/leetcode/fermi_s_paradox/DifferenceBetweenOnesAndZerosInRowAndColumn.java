package dsa.leetcode.fermi_s_paradox;

public class Solution {
  /**
   * https://leetcode.com/problems/difference-between-ones-and-zeros-in-row-and-column/
   * 
   * The solution involves calculating the difference matrix by counting the number of ones in each row and column,
   * and subtracting it from the number of zeros. This is efficiently stored and used to compute the difference
   * in one pass over the entire matrix.
   * 
   * TC: O(m * n) SC: O(m + n)
   * #array #matrix #simulation #medium
   */

  public int[][] onesMinusZeros(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[] rowOnes = new int[m];
    int[] colOnes = new int[n];

    // Count ones in each row and column
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (mat[i][j] == 1) {
          rowOnes[i]++;
          colOnes[j]++;
        }
      }
    }

    int[][] result = new int[m][n];

    // Compute the difference matrix
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        result[i][j] = 2 * rowOnes[i] + 2 * colOnes[j] - n - m;
      }
    }

    return result;
  }
}