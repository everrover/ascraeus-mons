package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class MaximumSubarraySumWithLengthDivisibleByK {
  /**
   * https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/
   *
   * To find the maximum sum of a subarray where the length is divisible by k, maintain a prefix array of length k that tracks the minimum prefix sum mod k. Utilize this information to calculate the maximum sum for each subarray ending at different indices.
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