package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class BrickWall {
  /**
   * https://leetcode.com/problems/brick-wall/
   *
   * To minimize the number of crossed bricks, find edges where the bricks end more frequently.
   * We count the frequencies of the edges not including the right-most of each row.
   * The line will pass through the least number of bricks at a position with the maximum number of edges.
   *
   * TC: O(n) where n is the total number of bricks
   * SC: O(m) where m is the number of distinct edge positions
   * #array #hash-table #medium
   */
  public int leastBricks(List<List<Integer>> wall) {
    Map<Integer, Integer> map = new HashMap<>();
    for (List<Integer> row : wall) {
      int num = 0;
      for (int i = 0; i < row.size() - 1; i++) {
        num += row.get(i);
        map.putIfAbsent(num, 0);
        map.put(num, map.get(num) + 1);
      }
    }
    int res = 0;
    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      res = Math.max(res, e.getValue());
    }
    return wall.size() - res;
  }
}