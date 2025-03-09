package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class SortMatrixByDiagonals {

  /**
   * https://leetcode.com/problems/sort-matrix-by-diagonals/description/
   *
   * Sorts the diagonals of a square matrix. The diagonals in the bottom-left triangle (including the middle diagonal)
   * are sorted in non-increasing order, and the diagonals in the top-right triangle are sorted in non-decreasing order.
   *
   * TC: O(n^2 log n) SC: O(n)
   * #matrix #sorting #array #medium
   */

  public int[][] sortMatrix(int[][] grid) {
    if (grid.length == 0) return grid;
    final int m = grid.length, n = grid[0].length;
    for (int i = n - 1; i > 0; i--) {
      int[] tmp = new int[n - i];
      for (int j = 0, k = i, idx = 0; j < n && k < m; j++, k++, idx++) tmp[idx] = grid[j][k];
      Arrays.sort(tmp);
      for (int j = 0, k = i, idx = 0; j < n && k < m; j++, k++, idx++) grid[j][k] = tmp[idx];
    }
    for (int i = 0; i < m; i++) {
      int[] tmp = new int[m - i];
      for (int j = i, k = 0, idx = 0; j < n && k < m; j++, k++, idx++) tmp[idx] = grid[j][k];
      Arrays.sort(tmp);
      for (int l = 0; l < tmp.length / 2; l++) {
        int t = tmp[l];
        tmp[l] = tmp[tmp.length - l - 1];
        tmp[tmp.length - l - 1] = t;
      }
      for (int j = i, k = 0, idx = 0; j < n && k < m; j++, k++, idx++) grid[j][k] = tmp[idx];
    }
    return grid;
  }
}