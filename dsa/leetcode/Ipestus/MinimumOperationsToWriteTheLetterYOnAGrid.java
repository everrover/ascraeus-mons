package dsa.leetcode.Ipestus;

public class MinimumOperationsToWriteTheLetterYOnAGrid {

  /**
   * https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/description/
   *
   * Calculate minimum changes to shape the grid into a 'Y'.
   * Strategy involves counting operations needed to modify to Y and not-Y areas.
   *
   * TC: O(n^2) SC: O(1)
   * #matrix #array #medium
   */

  public int minOperations(int[][] grid) {
    int N = grid.length;
    int[] y = new int[3]; // Counter for the 'Y' pattern
    int[] body = new int[3]; // Counter for the rest
    
    for(int r = 0; r < N; r++) {
      for(int c = 0; c < N; c++) body[grid[r][c]]++;
    }

    // Count for the center vertical line
    for(int r = N/2, c = N/2; r < N; r++) y[grid[r][c]]++;

    // Calculating result
    body[0] -= y[0];
    body[1] -= y[1];
    body[2] -= y[2];
    int res = Integer.MAX_VALUE;
    for(int i = 0; i < 3; i++) {
      res = Math.min(res,
        y[(i+1)%3] + y[(i+2)%3] + body[i] + Math.min(
          body[(i+1)%3], body[(i+2)%3]
        )
      );
    }
    return res;
  }
}