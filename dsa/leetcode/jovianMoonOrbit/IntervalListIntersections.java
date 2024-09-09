package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class IntervalListIntersections {

  /**
   * https://leetcode.com/problems/interval-list-intersections/
   *
   * Kept track of the current intervals from both lists, moved to the next interval in respective list based on the comparisons of end times.
   * Basically it's a derivative of the merge step in merge sort.
   * 
   * TC: O(n+m) SC: O(n+m)
   * #array #two-pointers #medium #merge-sort
   */

  public int[][] intervalIntersection(int[][] f, int[][] s) {
    List<int[]> intersect = new LinkedList<>();
    int i=0, j=0;
    int[] inte = new int[2];
    while(i<f.length && j<s.length){
      if (f[i][1] < s[j][0]) ++i;
      else if (s[j][1] < f[i][0]) ++j;
      else {
        intersect.add(new int[]{Math.max(f[i][0],s[j][0]), Math.min(f[i][1],s[j][1])});
        if (f[i][1] < s[j][1]) ++i;
        else ++j;
      }
    }
    int [][]res = new int[intersect.size()][2];
    i=0;
    for(int []m: intersect){
      res[i++] = m;
    }
    return res;
  }
}