package dsa.leetcode.mithrim_montez;

import java.util.*;

public class ZigzagGridTraversalWithSkip {
  /**
   * https://leetcode.com/problems/zigzag-grid-traversal-with-skip/description/
   *
   * For odd rows, start from the second column and skip every other column; and 
   * rev the list before adding to the result list
   *
   * TC: O(m*n) SC: O(m*n)
   * #array #traversal #zigzag #easy
   */

  public List<Integer> zigzagTraversal(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    List<Integer> res = new LinkedList<>();
    for(int i = 0; i < m; i++) {
      List<Integer> tmp = new ArrayList<>();
      int j = (i % 2 == 1) ? 1 : 0;
      for(; j < n; j += 2) {
        tmp.add(grid[i][j]);
      }
      if(i % 2 == 1) Collections.reverse(tmp);
      res.addAll(tmp);
    }
    return res;
  }
}