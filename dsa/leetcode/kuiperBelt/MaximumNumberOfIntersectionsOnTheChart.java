package dsa.leetcode.KuiperBelt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfIntersectionsOnTheChart {

  /**
   * https://leetcode.com/problems/maximum-number-of-intersections-on-the-chart/submissions/
   *
   * Basically lines are first mapped to regions on an acceptable scale. Since acceptable indexes are from 0...10^5, we
   * map 0...10^9 to 0...10^5. Also, since we have pivoting points (where m(slope) shifts from positive to negative), we
   * multiply the index by 2 (mark start of line at 2*idx and end of line at 2*idx+1) to make sure just intersections
   * are enough to compute the result.
   *
   * We could've placed the start and end of-lines at some other scale as well. eg: log, sqrt, etc.
   *
   * ALSO, duplicate lines are removed and there count stored.
   *
   * sum[idx] stores the count of lines at that have started till those indexes
   * cnt[idx] stores the count of lines at each index
   * max(sum[idx]+cnt[idx]) is the answer
   *
   * TC: O(nlogn) due to sorting, SC: O(n) for storing modified values and sum/cnt arrays.
   * #array #math #geometry #hard
   */

  public int maxIntersectionCount(int[] y) {
    if(y.length <= 2) return 1;
    Map<Integer, Integer> map = new HashMap<>();
    
    int []newy = new int[y.length];
    for(int i=0; i<y.length; i++) newy[i] = y[i];
    Arrays.sort(newy);
    for(int i=0; i<y.length; i++) map.put(newy[i], 2*i); // map to largest index
    for(int i=0; i<y.length; i++) newy[i] = map.get(y[i]);
    
    long sums[] = new long[y.length*2];
    int cnts[] = new int[y.length*2];
    cnts[newy[0]]++; // first line
    for(int i=1; i<y.length; i++) {
      int curr = newy[i], prev = newy[i-1];
      sums[Math.min(curr,prev)+1]++; // store start of line at 2*idx+1
      sums[Math.max(curr,prev)]--; // store end of line at 2*idx
      cnts[curr]++;
    }
    int res = 1;
    for(int i=0; i<sums.length; i++) {
      if(i>0) sums[i]+=sums[i-1]; // take sum from l...r to build prefix array (starts-ends) are stored since we add and subtract in respective indexes
      // from start to the end, sum stores counts of lines at each index: 2*idx has end of line and 2*idx+1 has start of line
      res = Math.max(res, (int)sums[i]+cnts[i]);
    }
    return res;
  }
}