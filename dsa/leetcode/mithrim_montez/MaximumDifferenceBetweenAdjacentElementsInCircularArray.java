package dsa.leetcode.mithrim_montez;

public class MaximumDifferenceBetweenAdjacentElementsInCircularArray {

  /**
   * https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/description/
   *
   * Find the maximum absolute difference between adjacent elements in a circular array.
   * Iterate through the array to calculate the difference between consecutive elements,
   * and consider the difference between the last and first elements for circular condition.
   *
   * TC: O(n) SC: O(1)
   * #array #circular-array #easy
   */

  public int maxAdjacentDistance(int[] nums) {
    int res = 0;
    // Traverse through the array and calculate the differences
    for(int i = 1; i < nums.length; i++) {
      res = Math.max(res, Math.abs(nums[i] - nums[i - 1]));
    }
    // Consider circular array condition
    return Math.max(res, Math.abs(nums[0] - nums[nums.length - 1]));
  }
}