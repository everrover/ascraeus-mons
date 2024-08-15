package dsa.leetcode.jovianMoonOrbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  /**
   * https://leetcode.com/problems/best-meeting-point/
   *
   * Calculate the minimal total travel distance by finding the median row and column
   * positions. Sum up the Manhattan distances from all '1's to these median points.
   *
   * TC: O(mn) SC: O(m+n)
   * #array #math #sorting #matrix #hard
   */
  private int m, n;
  public int minTotalDistance(int[][] grid) {
    m = grid.length; n = grid[0].length;
    int res = 0;
    List<Integer> rows = new ArrayList<>();
    List<Integer> cols = new ArrayList<>();
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(grid[i][j] == 1) {
          rows.add(i); cols.add(j);
        }
      }
    }
    // rows is sorted
    int midrow = rows.get(rows.size()/2);
    Collections.sort(cols);
    int midcol = cols.get(cols.size()/2);

    for(int row: rows) res += Math.abs(row-midrow);
    for(int col: cols) res += Math.abs(col-midcol);

    return res;
  }
}