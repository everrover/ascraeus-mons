package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class FourSum {
  /**
   * https://leetcode.com/problems/4sum/
   *
   * The approach is to use two pointers technique to find the unique quadruplets.
   * The outer two loops are fixed, and the inner two loops adjust to find the target sum.
   * Leverages sorting and skipping duplicates to ensure unique results.
   *
   * TC: O(n^3) SC: O(n)
   * #array #two-pointers #sorting #medium
   */

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < nums.length - 3; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      for (int j = i + 1; j < nums.length - 2; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) continue;
        int lo = j + 1, hi = nums.length - 1;
        while (lo < hi) {
          int currSum = nums[i] + nums[j] + nums[lo] + nums[hi];
          if (currSum < target || (lo > j + 1 && nums[lo] == nums[lo - 1])) {
            lo++;
          } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
            hi--;
          } else {
            res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
            lo++;
            hi--;
          }
        }
      }
    }
    return res;
  }
}