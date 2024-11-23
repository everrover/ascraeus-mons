package dsa.leetcode.jovianMoonOrbit;

public class DiagonalTraverse {
  /**
   * https://leetcode.com/problems/diagonal-traverse/
   *
   * The solution involves iterating through each diagonal starting from the top row or right column.
   * For each diagonal, if the diagonal index is even, we reverse the order of elements before storing them.
   *
   * TC: O(m * n) SC: O(1)
   * #array #matrix #simulation #medium
   */

  public int[] findDiagonalOrder(int[][] mat) {
    int ROW = mat.length, COL = mat[0].length;
    int[] res = new int[ROW * COL];
    int idx = 0;
    for (int i = 0; i < COL + ROW - 1; i++) {
      // if i<COL, it's the top row, else it's the right column
      int r = i < COL ? 0 : (i - COL + 1);
      int c = i < COL ? i : (COL - 1);
      int sz = 0;
      while (r < ROW && c >= 0) {
        sz++;
        res[idx++] = mat[r][c];
        r++; c--;
      }
      if (i % 2 == 0) {
        for (int j = idx - sz, k = idx - 1; j < k; j++, k--) {
          int tmp = res[j];
          res[j] = res[k];
          res[k] = tmp;
        }
      }
    }
    return res;
  }
}