package dsa.leetcode.mithrim_montez;

import java.util.*;

public class SumOfVariableLengthSubarrays {
  
  /**
   * https://leetcode.com/problems/sum-of-variable-length-subarrays/description/
   *
   * Added simple B.F. sol. If constraints are large, we can use prefix sum to solve this problem.
   *
   * TC: O(n^2) SC: O(1)
   * #bruteforce #subarray #sum #easy
   */
  
  public int subarraySum(int[] nums) {
    int res = 0;
    for(int i=0; i<nums.length; i++){
      int st = Math.max(0, i-nums[i]);
      for(int j=st; j<=i; j++){
        res += nums[j];
      }
    }
    return res;
  }
}