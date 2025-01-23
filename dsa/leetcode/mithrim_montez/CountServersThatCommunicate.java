package dsa.leetcode.mithrim_montez;

public class CountServersThatCommunicate {

  /**
   * https://leetcode.com/problems/count-servers-that-communicate/description/?envType=daily-question&envId=2025-01-23
   *
   * Calculate the number of servers on each row and column, then count all servers
   * not isolated (i.e., that have row or column count greater than one).
   *
   * TC: O(m * n) SC: O(m + n)
   * #array #matrix #counting #medium
   */

  public int countServers(int[][] grid) {
      int m = grid.length, n = grid[0].length;
      int[] r = new int[m];
      int[] c = new int[n];

      // Count servers in each row and column
      for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
              if (grid[i][j] == 1) {
                  r[i]++;
                  c[j]++;
              }
          }
      }

      int res = 0;
      // Count non-isolated servers
      for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
              if (grid[i][j] == 1 && (r[i] > 1 || c[j] > 1)) {
                  res++;
              }
          }
      }
      return res;
  }
}