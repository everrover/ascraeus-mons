package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class FindMaximumAreaOfATriangle {
  
  /**
   * https://leetcode.com/problems/find-maximum-area-of-a-triangle/
   *
   * Given a set of coordinates, compute twice the maximum area of a triangle
   * where at least one side is parallel to the x-axis or y-axis.
   *
   * While going for triangle with base parallel to x-axis, we can go through all points
   * with the same x-coordinate and find the maximum and minimum y-coordinates to compute the base.
   * The height can be computed using the maximum and minimum x-coordinates of the points w.r.t. curr x.
   *
   * Same for triangle with base parallel to y-axis.
   *
   * TC: O(n log n) due to sorting
   * SC: O(1) 
   * #geometry #sorting #greedy #medium
   */
  
  public long maxArea(int[][] coords) {
    long res = -1L;
    Arrays.sort(coords, (a,b) -> (a[0] - b[0]));
    long maxx = coords[0][0], minx = coords[coords.length-1][0];
    long maxy = -1, miny = Long.MAX_VALUE;
    int i = 0;
    while(i < coords.length) {
      int j = i;
      maxy = -1; miny = Long.MAX_VALUE;
      while(j < coords.length && coords[j][0] == coords[i][0]) {
        maxy = Math.max(maxy, coords[j][1]);
        miny = Math.min(miny, coords[j][1]);
        j++;
      }
      // Calculate area using maximum and minimum y values maintaining fixed x
      res = Math.max(res, Math.max(
        Math.abs(maxx-coords[i][0])*(maxy-miny),
        Math.abs(minx-coords[i][0])*(maxy-miny)
      ));
      i = j;
    }
    Arrays.sort(coords, (a,b) -> (a[1] - b[1]));
    maxy = coords[0][1]; miny = coords[coords.length-1][1];
    i = 0;
    while(i < coords.length) {
      int j = i;
      maxx = -1; minx = Long.MAX_VALUE;
      while(j < coords.length && coords[j][1] == coords[i][1]) {
        maxx = Math.max(maxx, coords[j][0]);
        minx = Math.min(minx, coords[j][0]);
        j++;
      }
      // Calculate area using maximum and minimum x values maintaining fixed y
      res = Math.max(res, Math.max(
        Math.abs(maxy-coords[i][1])*(maxx-minx),
        Math.abs(miny-coords[i][1])*(maxx-minx)
      ));
      i = j;
    }
    return res == 0 ? -1 : res;
  }
}