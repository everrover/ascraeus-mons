package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class MaximumSubarraySumWithLengthDivisibleByK {
  /**
   * https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/
   *
   * Did a similar one before.
   * 
   * For each index i, we've to find the max-sum, at indexes j, where j % k == i % k.
   * To maximize the sum, we need to minimize the prefix sum at each index j.
   * So, we keep track of the minimum prefix sum at each index j % k.
   * 
   * TC: O(n) SC: O(k)
   * #array #hash-table #prefix-sum #medium
   */

  public long maxSubarraySum(int[] A, int k) {
    long[] prefix = new long[k];
    Arrays.fill(prefix, (long) 1e15);
    prefix[k - 1] = 0;
    long res = (long) -1e15, currprefix = 0;
    for (int i = 0; i < A.length; i++) {
      currprefix += A[i];
      res = Math.max(res, currprefix - prefix[i % k]);
      prefix[i % k] = Math.min(prefix[i % k], currprefix);
    }
    return res;
  }
}