package dsa.leetcode.mithrim_montez;

import java.util.*;

public class ZigzagGridTraversalWithSkip {
  /**
   * https://leetcode.com/problems/zigzag-grid-traversal-with-skip/description/
   *
   * Traverse the grid in a zigzag fashion while skipping every alternate cell.
   * Start from the top-left, traverse right for even-indexed rows and left for odd-indexed rows, skipping every second cell.
   *
   * TC: O(m*n) SC: O(m*n)
   * #array #traversal #zigzag #easy
   */

  public List<Integer> zigzagTraversal(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    List<Integer> res = new LinkedList<>();
    for(int i = 0; i < m; i++) {
      List<Integer> tmp = new ArrayList<>();
      int j = 0;
      if(i % 2 == 1) j = 1;
      for(; j < n; j += 2) {
        tmp.add(grid[i][j]);
      }
      if(i % 2 == 1) Collections.reverse(tmp);
      res.addAll(tmp);
    }
    return res;
  }
}