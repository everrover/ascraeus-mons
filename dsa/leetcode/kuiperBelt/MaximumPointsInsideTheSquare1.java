package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-points-inside-the-square/submissions/
 * The second largest co-ordinate for any given `tag` is the max square limit for the problem(non-inclusive).
 * One array is used to track the smallest sq for a tag, second tagged point is tracked separately(array not needed since for any of the second largest we only need the smallest one).
 * 
 * TC: O(3*n=n) SC: O(n)
 * #array #hash-table #string #binary-search #sorting #medium
 */
public class MaximumPointsInsideTheSquare {

  public int maxPointsInsideSquare(int[][] points, String s) {
    int secondTagged = Integer.MAX_VALUE;
    int[] firstTagged = new int[26];
    Arrays.fill(firstTagged, Integer.MAX_VALUE);
    for (int i = 0; i < points.length; i++) {
      int[] pt = points[i];
      int c = s.charAt(i) - 'a';
      int maxsq = Math.max(Math.abs(pt[0]), Math.abs(pt[1]));
      if (firstTagged[c] > maxsq) {
        int tmp = firstTagged[c];
        firstTagged[c] = maxsq;
        maxsq = tmp;
      }
      secondTagged = Math.min(maxsq, secondTagged);
    }
    int res = 0;
    for (int i = 0; i < points.length; i++) {
      int[] pt = points[i];
      if (pt[0] >= secondTagged || pt[0] <= -secondTagged || pt[1] >= secondTagged || pt[1] <= -secondTagged) continue;
      res++;
    }
    return res;
  }
}
