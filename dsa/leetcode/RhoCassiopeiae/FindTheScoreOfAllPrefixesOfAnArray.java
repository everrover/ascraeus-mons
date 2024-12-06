package dsa.leetcode.RhoCassiopeiae;

public class Solution {
  /**
   * https://leetcode.com/problems/find-the-score-of-all-prefixes-of-an-array/
   *
   * Iterate through the array while maintaining the maximum value
   * for each prefix to calculate its conversion value. Update the 
   * running sum to derive the prefix scores.
   *
   * TC: O(n) SC: O(1)
   * #array #prefix-sum #medium
   */

  public long[] findPrefixScore(int[] nums) {
    long[] res = new long[nums.length];
    if (nums.length == 0) return res;
    long sum = 0, maxval = 0;
    for (int i = 0; i < nums.length; i++) {
      maxval = Math.max(maxval, nums[i]);
      res[i] = sum + maxval + nums[i]; // Compute score for current prefix
      sum = res[i]; // Update cumulative sum
    }
    return res; // Return the score of all prefixes
  }
}