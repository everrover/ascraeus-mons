package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class PartitionArraySuchThatMaximumDifferenceIsK {
  /**
   * https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/?envType=daily-question&envId=2025-06-19
   *
   * The goal is to partition the array into the minimum number of subsequences such that the difference
   * between the maximum and minimum values in each subsequence is at most k. First, we sort the array.
   * Then, iterate through it and start a new subsequence whenever the current element exceeds the initial
   * element of the subsequence by more than k.
   *
   * TC: O(n log n) due to sorting. SC: O(1) for constant space usage.
   * #array #greedy #sorting #medium
   */

  public int partitionArray(int[] nums, int k) {
    Arrays.sort(nums); // Sort the array to ensure sequential processing
    int res = 0, i = 0;
    while (i < nums.length) { // Iterate through the array
      int j = i;
      while (j < nums.length && nums[j] - nums[i] <= k) j++; // Find elements within the k range
      res++; // Increment for each subsequence
      i = j; // Move to the next subsequence starting point
    }
    return res;
  }
}