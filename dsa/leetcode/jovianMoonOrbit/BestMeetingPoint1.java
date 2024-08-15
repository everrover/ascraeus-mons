package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

/**
 * https://leetcode.com/problems/best-meeting-point/
 *
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * 
 * TC: O(mn) SC: O(m+n)
 * #array #math #sorting #matrix #hard
 */
public class BestMeetingPoint {
  private int m, n;
  public int minTotalDistance(int[][] grid) {
    m = grid.length; n = grid[0].length;
    int res = 0;
    List<Integer> rows = new ArrayList<>();
    List<Integer> cols = new ArrayList<>();
    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(grid[i][j] == 1) {
          rows.add(i); cols.add(j);
        }
      }
    }
    // rows is sorted
    int midrow = rows.get(rows.size() / 2);
    Collections.sort(cols); // sorting cols
    int midcol = cols.get(cols.size() / 2);

    for(int row : rows) res += Math.abs(row - midrow);
    for(int col : cols) res += Math.abs(col - midcol);

    return res;
  }
}