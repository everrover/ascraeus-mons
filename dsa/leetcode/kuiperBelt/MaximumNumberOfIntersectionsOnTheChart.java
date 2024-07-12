package dsa.leetcode.kuiperBelt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfIntersectionsOnTheChart {

  /**
   * https://leetcode.com/problems/maximum-number-of-intersections-on-the-chart/submissions/
   * The solution counts intersections on a sorted modified array representation.
   * Using a Map to track original values with modified representation to maintain sorting order.
   * Prefix sum technique is applied to count intersections efficiently.
   * 
   * TC: O(nlogn) due to sorting, SC: O(n) for storing modified values and sum/cnt arrays.
   * #array #math #binary-indexed-tree #geometry #hard
   */

  public int maxIntersectionCount(int[] y) {
    if(y.length <= 2) return 1;
    Map<Integer, Integer> map = new HashMap<>();
    
    int []newy = new int[y.length];
    for(int i=0; i<y.length; i++) newy[i] = y[i];
    Arrays.sort(newy);
    for(int i=0; i<y.length; i++) map.put(newy[i], 2*i);
    for(int i=0; i<y.length; i++) newy[i] = map.get(y[i]);
    
    long sums[] = new long[y.length*2];
    int cnts[] = new int[y.length*2];
    cnts[newy[0]]++;
    for(int i=1; i<y.length; i++) {
      int curr = newy[i], prev = newy[i-1];
      sums[Math.min(curr,prev)+1]++;
      sums[Math.max(curr,prev)]--;
      cnts[curr]++;
    }
    int res = 1;
    for(int i=0; i<sums.length; i++) {
      if(i>0) sums[i]+=sums[i-1];
      res = Math.max(res, (int)sums[i]+cnts[i]);
    }
    return res;
  }
}