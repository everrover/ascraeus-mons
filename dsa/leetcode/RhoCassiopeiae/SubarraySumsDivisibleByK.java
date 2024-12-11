package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/subarray-sums-divisible-by-k/
   *
   * Find the number of non-empty subarrays with a sum divisible by k.
   * Calculate the running prefix sum and track the remainder modulo k.
   * Use a hashmap to count the number of times each remainder occurs.
   * If the remainder is seen before, add its count to the result.
   *
   * TC: O(n) SC: O(k)
   * #array #hash-table #prefix-sum #medium
   */

  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int prefix = 0, res = 0;
    for(int num: nums) {
      prefix = (prefix + num % k + k) % k;
      if(map.containsKey(prefix)) res += map.get(prefix);
      map.put(prefix, map.getOrDefault(prefix, 0) + 1);
    }
    return res;
  }
}