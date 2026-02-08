package dsa.leetcode.Ipestus;

public class FindTheMinimumAreaToCoverAllOnesI {

  /**
   * https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/description/
   *
   * Identify the minimum and maximum row and column indices containing '1'.
   * Calculate the width and height based on these indices to derive the minimum area.
   * 
   * TC: O(n * m) SC: O(1)
   * #array #matrix #medium
   */

  public int minimumArea(int[][] grid) {
    final int len = grid.length, dep = grid[0].length;
    int maxr = -1, minr = len + 1, maxc = -1, minc = dep + 1;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < dep; j++) {
        if (grid[i][j] == 1) {
          // Update minimum and maximum row and column indices
          maxr = Math.max(maxr, i);
          minr = Math.min(minr, i);
          maxc = Math.max(maxc, j);
          minc = Math.min(minc, j);
        }
      }
    }
    return (maxr - minr + 1) * (maxc - minc + 1);
  }
}