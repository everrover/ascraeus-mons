package dsa.leetcode.VallesMarineris;

public class MaximumCountOfPositiveIntegerAndNegativeInteger {
  /**
   * https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/description/?envType=daily-question&envId=2025-03-12
   *
   * The solution involves iterating through the array, counting the positive and negative integers.
   * After counting, the maximum of these counts is returned.
   *
   * TC: O(n) SC: O(1)
   * #array #counting #easy
   */

  public int maximumCount(int[] nums) {
    int cnt = 0, neg = 0;
    for(int n: nums){
      if(n>0)cnt++;
      else if(n<0)neg++;
    }
    return Math.max(neg, cnt);
  }
}