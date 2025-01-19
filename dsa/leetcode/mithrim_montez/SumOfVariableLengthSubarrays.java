package dsa.leetcode.mithrim_montez;

import java.util.*;

public class SumOfVariableLengthSubarrays {
  
  /**
   * https://leetcode.com/problems/sum-of-variable-length-subarrays/description/
   *
   * For each index i in the array, define a subarray nums[start ... i] where start = max(0, i - nums[i]).
   * Calculate the total sum of all elements from the subarray for each index.
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