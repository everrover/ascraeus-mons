package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class Solution {

  /**
   * https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/description/?envType=daily-question&envId=2025-06-18
   *
   * Sort the array and use a greedy approach to divide it into groups of three.
   * For each group, ensure that the maximum difference between any two elements is less than or equal to k.
   * If a valid grouping can't be made, return an empty array.
   *
   * TC: O(n log n) due to sorting, SC: O(n)
   * #array #greedy #sorting #medium
   */

  public int[][] divideArray(int[] nums, int k) {
    Arrays.sort(nums);  // Sort the array for easier grouping
    int[][] res = new int[nums.length / 3][3];
    for (int i = 3; i <= nums.length; i += 3) {
      // Check if the current group satisfies the condition
      if (Math.abs(nums[i - 1] - nums[i - 2]) > k ||
          Math.abs(nums[i - 3] - nums[i - 2]) > k ||
          Math.abs(nums[i - 1] - nums[i - 3]) > k) {
        return new int[0][0];  // Return empty if any condition is violated
      }
      // Assign the elements to the resulting group
      res[i / 3 - 1][0] = nums[i - 3];
      res[i / 3 - 1][1] = nums[i - 2];
      res[i / 3 - 1][2] = nums[i - 1];
    }
    return res;
  }
}