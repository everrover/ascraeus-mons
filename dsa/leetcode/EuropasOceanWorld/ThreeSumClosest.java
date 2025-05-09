package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class ThreeSumClosest {

  /**
   * https://leetcode.com/problems/3sum-closest/
   *
   * Given an integer array, use sorting and a two-pointer technique to find the three integers
   * whose sum is closest to the target. Iterate through the array and adjust the pointers
   * based on the current sum to find the optimal solution.
   *
   * TC: O(n^2) SC: O(1)
   * #array #two-pointers #sorting #medium
   */

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int l, r, ans = Integer.MAX_VALUE, sum, diff;
    for (int i = 0; i < nums.length && ans != target; i++) {
      l = i + 1;
      r = nums.length - 1;
      while (l < r) {
        sum = nums[l] + nums[r] + nums[i];
        if (Math.abs(target - sum) < Math.abs(target - ans)) {
          ans = sum;
        }
        if (sum < target) {
          l++;
        } else {
          r--;
        }
      }
    }
    return ans;
  }
}