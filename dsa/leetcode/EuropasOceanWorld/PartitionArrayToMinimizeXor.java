package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class PartitionArrayToMinimizeXor {

  /**
   * https://leetcode.com/problems/partition-array-to-minimize-xor/
   * 
   * Given an integer array and an integer k, partition the array into k non-empty subarrays. Compute the bitwise XOR for each subarray and return the minimum possible value of the maximum XOR among these subarrays.
   * 
   * The approach uses dynamic programming with memoization. Define dp[i][j] as the minimum possible "max-XOR" when splitting the first i elements into j parts. For each dp[i][j], try all splits and take the minimum over the maximum of subarray XORs.
   * 
   * TC: O(n^2 * k) SC: O(n * k)
   * #array #dynamic-programming #bit-manipulation #prefix-sum #medium
   */
  
  public int minXor(int[] nums, int k) {
    int [][]dp = new int[nums.length][k+1];
    for(int []dd: dp) Arrays.fill(dd, -1);
    return dfs(0, k, nums, dp);
  }

  private int dfs(int idx, int k, final int[] nums, final int[][] dp){
    if(idx >= nums.length) {
      return k == 0 ? 0 : Integer.MAX_VALUE;
    }
    if(dp[idx][k] != -1) return dp[idx][k];
    int xor = 0, res = Integer.MAX_VALUE;
    for(int i = idx; i < nums.length; i++) {
      xor ^= nums[i];
      res = Math.min(res, Math.max(xor, dfs(i+1, k-1, nums, dp)));
    }
    return dp[idx][k] = res;
  }

}