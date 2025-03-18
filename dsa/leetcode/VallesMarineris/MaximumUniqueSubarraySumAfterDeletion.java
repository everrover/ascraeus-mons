package dsa.leetcode.VallesMarineris;

import java.util.HashSet;
import java.util.Set;

public class MaximumUniqueSubarraySumAfterDeletion {

  /**
   * https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/description/
   *
   * The solution involves deleting elements with value less than zero and then selecting all unique positive values.
   * The maximum sum can be the sum of these unique values or the maximum element if all are negative or zero.
   *
   * TC: O(n) SC: O(n)
   * #array #hash-table #greedy #easy
   */

  public int maxSum(int[] nums) {
    Set<Integer> s = new HashSet<>();
    int max = -101;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] >= 0) {
        s.add(nums[i]);
      }
      max = Math.max(max, nums[i]);
    }
    int sum = 0;
    for (int n : s) {
      sum += n;
    }
    return Math.max(max, s.size() > 0 ? sum : -101);
  }
}