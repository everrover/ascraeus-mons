package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MaximumSumWithAtMostKElements {
  /**
   * https://leetcode.com/problems/maximum-sum-with-at-most-k-elements/description/
   *
   * Trivial greedy choice : 
   * Sort each row in descending order and take the top elements as specified by the limits for each row.
   * Use a max-heap to extract the largest `k` elements efficiently and compute the sum.
   *
   * TC: O(n * m log m + k log n) SC: O(n * m)
   * #array #greedy #sorting #heap #matrix #medium
   */

   public long maxSum(int[][] grid, int[] limits, int k) {
    Queue<Integer> pq = new PriorityQueue<>();
    for(int []g: grid) Arrays.sort(g);
    for(int j=0; j<grid.length; j++){
      int []g = grid[j];
      for(int i=0; i<limits[j] && i<g.length; i++) pq.offer(g[g.length-1-i]);
      while(pq.size()>k) pq.poll();
    }
    long res = 0;
    while(!pq.isEmpty()){
      res += pq.poll();
    }
    return res;
  }
}