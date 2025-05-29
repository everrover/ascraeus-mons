package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class CapacityToShipPackagesWithinDDays {

  /**
   * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/submissions/1648281077/?envType=company&envId=agoda&favoriteSlug=agoda-all
   *
   * Using binary search to determine the minimal ship capacity needed. 
   * Calculate the middle capacity and determine if it's possible to ship within the given days. 
   * Adjust the search range based on feasibility to locate the minimal capacity.
   *
   * TC: O(n log w), where n is the number of packages and w is the possible capacity range.
   * SC: O(1)
   * #array #binary-search #medium
   */

  public int shipWithinDays(int[] weights, int D) {
    int left = 0, right = 0;
    for (int w : weights) {
      left = Math.max(left, w);
      right += w;
    }
    while (left < right) {
      int mid = (left + right) / 2, need = 1, currship = 0;
      for (int w : weights) {
        if (currship + w > mid) {
          need += 1;
          currship = 0;
        }
        currship += w;
      }
      if (need > D) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}