package dsa.leetcode.fermi_s_paradox;

public class NumberOfWaysToSplitArray {
  
  /**
   * https://leetcode.com/problems/number-of-ways-to-split-array/description/?envType=daily-question&envId=2025-01-03
   *
   * `prec` is the prefix sum of the left subarray. `pre` is the total sum of the array.
   * `pre - prec` is the prefix sum of the right subarray.
   * reqd cindition: prec <= pre - prec ... prec + prec <= pre ... 2 * prec <= pre
   * Increment counts when the condition is met.
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