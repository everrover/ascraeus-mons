package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-hamming-distances/
 * This solution involves representing the integers in a binary format and then calculating
 * the Hamming distance by comparing bit positions. A dynamic programming approach is used
 * to keep track of the maximum Hamming distance.
 *
 * ```
 * maxHamming(num[any in 1...2^m], k[distance]) = max(maxHamming(num, k-1), maxHamming(num ^ (1 << bit), k-1)), bit = [0 ... max-allowed-bits]
 *                                              = 1, num = [-part-of-provided-num-set]
 *                                              = -inf, k == 0 || num-is-already-visited
 * ```
 *
 * TC: O(m*2^m) SC: O(2^m)
 * #array #bit-manipulation #breadth-first-search #hard
 */
public class MaxHammingDistances {

  public int[] maxHammingDistances(int[] nums, int m) {
    int []res = new int[nums.length];
    int maxsz = (1 << m);
    int []dp = new int[maxsz];
    Arrays.fill(dp, Integer.MIN_VALUE);
    for(int num: nums) dp[num] = 0;
    for(int bit=0; bit<m; bit++){
      int []prevLayer = dp.clone();
      for(int n=0; n<maxsz; n++){
        dp[n] = Math.max(dp[n], prevLayer[n ^ (1 << bit)] + 1);
      }
    }
    for(int i=0; i<res.length; i++){
      res[i] = dp[nums[i]];
    }
    return res;
  }
}
