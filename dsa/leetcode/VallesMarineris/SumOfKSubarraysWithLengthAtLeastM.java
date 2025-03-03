package dsa.leetcode.VallesMarineris;

import java.util.*;

public class SumOfKSubarraysWithLengthAtLeastM {

  /**
   * https://leetcode.com/problems/sum-of-k-subarrays-with-length-at-least-m/description/
   *
   * Use dynamic programming to track the maximum sum of k non-overlapping
   * subarrays where each has a length of at least m. Prefix sums are used
   * for quick subarray sum calculations.
   *
   * TC: O(n*k) SC: O(n*k)
   * #dynamic-programming #prefix-sum #medium
   */

  public int maxSum(int[] nums, int k, int m) {
    int n = nums.length;
    if(k * m > n) return 0; // impossible to form k subarrays of length at least m
    int[][][] dp = new int[2][n+1][k+1];
    int[] p = new int[n+1];
    for(int i = 1; i <= n; i++) p[i] = p[i-1] + nums[i-1];
    
    // Fill dp array
    for(int i = m; i <= n; i++) {
        for(int j = 1; j <= k; j++) {
            dp[i%2][i][j] = Math.max(dp[(i-1)%2][i-1][j], dp[(i-m)%2][i-m][j-1] + p[i] - p[i-m]);
        }
    }

    return dp[n%2][n][k];
  }
}