package dsa.leetcode.JupitersGreatStorm;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsK {
  
  /**
   * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/description/?envType=weekly-question&envId=2025-04-15
   *
   * Compute the prefix sum and use a hashmap to efficiently find the subarrays with sum equal to k.
   * Store prefix sums and their indices in the hashmap.
   * Update the result if a matching prefix sum is found.
   * 
   * TC: O(n) SC: O(n)
   * #array #hash-table #prefix-sum #medium
   */  
  
    public int maxSubArrayLen(int[] nums, int k) { 
      int res = 0; 
      int sum = 0; 
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      for (int i = 0; i < nums.length; i++) { 
        sum += nums[i]; 
        if (!map.containsKey(sum)) map.put(sum, i); 
        if (map.containsKey(sum - k)) 
          res = Math.max(res, i - map.get(sum - k));
      } 
      return res; 
    }
}