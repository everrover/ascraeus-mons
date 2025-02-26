package dsa.leetcode.VallesMarineris;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/maximum-sum-with-at-most-k-elements/description/
   *
   * Sort each row in descending order and take the top elements as specified by the limits for each row.
   * Use a max-heap to extract the largest `k` elements efficiently and compute the sum.
   *
   * TC: O(n * m log m + k log n) SC: O(n * m)
   * #array #greedy #sorting #heap #matrix #medium
   */

  public long maxSum(int[][] grid, int[] limits, int k) {
    Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    long res = 0;
    for (int[] g : grid) {
      Arrays.sort(g);
      for (int j = 0; j < g.length; j++) {
        int limit = limits[j];
        for (int i = 0; i < limit && i < g.length; i++) {
          pq.offer(g[g.length - 1 - i]);
        }
      }
    }

    while (!pq.isEmpty() && k > 0) {
      res += pq.poll();
      k--;
    }

    return res;
  }
}