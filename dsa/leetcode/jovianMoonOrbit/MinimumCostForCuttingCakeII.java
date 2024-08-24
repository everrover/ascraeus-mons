package dsa.leetcode.jovianMoonOrbit;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-cost-for-cutting-cake-ii/
 *
 * Greedy approach where at each step, we perform a cut on the line with the highest cost.
 * If you perform a horizontal cut, it affects the horizontal cuts below.
 * If you perform a vertical cut, it affects the vertical cuts to the right.
 *
 * TC: O((m+n)log(m+n)) SC: O(1)
 * #array #greedy #sorting #hard
 */
public class MinimumCostForCuttingCakeII {

  public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
    long res = 0;
    Arrays.sort(horizontalCut);
    Arrays.sort(verticalCut);
    int i = horizontalCut.length - 1, j = verticalCut.length - 1;
    while (i >= 0 && j >= 0) {
      if (verticalCut[j] >= horizontalCut[i]) {
        res += verticalCut[j] * (m - i - 1);
        j--;
      } else {
        res += horizontalCut[i] * (n - j - 1);
        i--;
      }
    }
    while (i >= 0) {
      res += horizontalCut[i--] * (n - j - 1);
    }
    while (j >= 0) {
      res += verticalCut[j--] * (m - i - 1);
    }
    return res;
  }
}