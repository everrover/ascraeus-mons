package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class PartitionArraySuchThatMaximumDifferenceIsK {
  /**
   * https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/?envType=daily-question&envId=2025-06-19
   *
   * Sort the array and iterate through it. For each element, extend the subsequence as far as possible
   * without exceeding the maximum difference k. Increment the count of subsequences whenever a new subsequence
   * is started.
   * 
   * TC: O(n log n) SC: O(1)
   * #array #greedy #sorting #medium
   */
  
  public int partitionArray(int[] nums, int k) {
    Arrays.sort(nums);  // Sort the array
    int res = 0, i = 0;
    while (i < nums.length) {
      int j = i;
      while (j < nums.length && nums[j] - nums[i] <= k) j++;  // Extend the subsequence
      res++;  // Increment the subsequence count
      i = j;  // Move to the next subsequence
    }
    return res;
  }
}