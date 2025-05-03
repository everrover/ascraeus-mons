package dsa.leetcode.KuiperBelt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/best-meeting-point/
 * 
 * TL;DR: Used the median point for finding optimal point
 * 
 * For 1 0 0 0 1 1 1 1 => for 1st as optimal point, distance = 0+4+5+6+7 = 22. similarly applying for all
 * => 22 19 16 13 10 9 10 12
 * 
 * If we apply for any combination of 1s, median is always the optimal point
 * simply if we resolve min(|x-x1|+|y-y1|) for all (x1, y1), we get the median point
 * 
 * Learning - Look for mean, mode, median and square root for optimization problems. Possible results can be obtained via them.
 * 
 * Tried BFS but it worked in O((m*n)^2) TC, so TLE
 * 
 * TC: O(nlogn+m*n) SC: O(m+n)
 * 
 * #array #math #sorting #matrix #hard
 */

public class BestMeetingPoint {

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
    // rows is sorted assuming input is in sorted manner
    int midrow = rows.get(rows.size()/2);
    Collections.sort(cols);
    int midcol = cols.get(cols.size()/2);

    for(int row: rows) res += Math.abs(row - midrow);
    for(int col: cols) res += Math.abs(col - midcol);

    return res;
  }
}