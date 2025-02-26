package dsa.leetcode.VallesMarineris;

public class MaximumAbsoluteSumOfAnySubarray {

  /**
   * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/?envType=daily-question&envId=2025-02-26
   *
   * By iterating through the array and keeping track of the maximum and minimum possible subarray sums,
   * we can calculate the maximum absolute sum either by the maximum sum or the minimum sum using Kadane's algorithm.
   *
   * TC: O(n) SC: O(1)
   * #array #dynamic-programming #medium
   */

  public int maxAbsoluteSum(int[] nums) {
    int maxsum = 0, minsum = 0, res = 0;
    for(int num: nums) {
      // Update the results with the max of previous results or current subarray calculations
      res = Math.max(res, Math.max(Math.abs(minsum), maxsum));
      // Calculate maximum subarray sum ending at current position
      maxsum = Math.max(maxsum + num, 0);
      // Calculate minimum subarray sum ending at current position
      minsum = Math.min(minsum + num, 0);
    }
    return res;
  }
}