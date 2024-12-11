package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class SubarraySumsDivisibleByK {
  /**
   * https://leetcode.com/problems/subarray-sums-divisible-by-k/
   *
   * if sum(i,j) is divisible by k, then sum(0,j) - sum(0,i-1) is divisible by k
   * => (prefix_j - prefix_(i-1)) % k == 0
   * => prefix_j % k == prefix_(i-1) % k
   * 
   * so we hash the prefix-sum % k and count the occurences of the same prefix sum and those
   * can be used to form subarrays divisible by k.
   *
   * TC: O(n) SC: O(k)
   * #array #hash-table #prefix-sum #medium
   */

  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int prefix = 0, res = 0;
    for(int num: nums) {
      prefix = (prefix + num % k + k) % k; // handle negative modulus with num % k + k
      if(map.containsKey(prefix)) res += map.get(prefix);
      map.put(prefix, map.getOrDefault(prefix, 0) + 1);
    }
    return res;
  }
}