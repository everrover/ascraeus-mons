package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/make-sum-divisible-by-p/
   *
   * Use prefix sums to calculate the subarray sums and a map to keep track of the rightmost index for every prefix sum % p.
   * The goal is to find a subarray that when removed, results in the remaining sum being divisible by p.
   *
   * TC: O(n) SC: O(n)
   * #array #hash-table #prefix-sum #medium
   */

  public int minSubarray(int[] nums, int p) {
    int SUMMOD = 0, prefix = 0, res = nums.length;
    // Calculate total sum's modulus with p
    for(int num: nums) {
      SUMMOD += num;
      SUMMOD %= p;
    }
    if(SUMMOD == 0) return 0; // If sum is already divisible
    Map<Integer, Integer> prevmods = new HashMap<>();
    prevmods.put(0, -1);
    for(int idx=0; idx<nums.length; idx++) {
      prefix = (prefix + nums[idx]) % p;
      int currmod = (prefix - SUMMOD + p) % p; // Calculate required mod
      if(prevmods.containsKey(currmod)) {
        res = Math.min(res, idx - prevmods.get(currmod));
      }
      prevmods.put(prefix, idx);
    }
    return res == nums.length ? -1 : res;
  }
}