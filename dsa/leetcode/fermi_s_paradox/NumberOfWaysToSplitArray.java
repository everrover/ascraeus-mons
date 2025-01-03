package dsa.leetcode.fermi_s_paradox;

public class Solution {
  
  /**
   * https://leetcode.com/problems/number-of-ways-to-split-array/description/?envType=daily-question&envId=2025-01-03
   *
   * To find the number of valid splits in the array, maintain a running prefix sum for the left partition
   * and compare it with the right partition's sum, which can be derived from the total sum minus the prefix sum.
   * Increment counts when the prefix sum is at least half the total sum, excluding the current element.
   * Traverse the array up to the second last element.
   * 
   * TC: O(n) SC: O(1)
   * #array #prefix-sum #medium
   */

  public int waysToSplitArray(int[] nums) {
    long pre = 0L, prec = 0L;
    for (int i = 0; i < nums.length; i++) {
      pre += nums[i];
    }
    int res = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      prec += nums[i];
      if (2 * prec >= pre) {
        res++;
      }
    }
    return res;
  }
}