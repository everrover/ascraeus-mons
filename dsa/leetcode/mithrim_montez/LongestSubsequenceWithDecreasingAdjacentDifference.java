package dsa.leetcode.mithrim_montez;

import java.util.*;

public class LongestSubsequenceWithDecreasingAdjacentDifference {

  /**
   * https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference/
   *
   * Use dynamic programming.
   * Store the maximum answer for each index and every possible difference.
   *
   * TC: O(n^2) SC: O(n*abs)
   * #array #dynamic-programming #medium
   */

  public int longestSubsequence(int[] nums) {
    int [][]dp = new int[nums.length][301];
    for(int []d: dp) Arrays.fill(d, -1);
    int res = 0;
    for(int i=0; i<nums.length; i++){
      res = Math.max(res, 1+dfs(i, 300, dp, nums));
      if(cabs <= abs) res = Math.max(res, 1+dfs(i, cabs, dp, nums));
    }
    return res;
  }

  private int dfs(int idx, int abs, final int [][]dp, final int []nums){
    if(idx >= nums.length) return 0;
    if(dp[idx][abs] != -1) return dp[idx][abs];
    int res = 0;
    for(int i=idx+1; i<nums.length; i++){
      int cabs = Math.abs(nums[i] - nums[idx]);
      if(cabs <= abs) res = Math.max(res, 1+dfs(i, cabs, dp, nums));
    }
    return dp[idx][abs] = res;
  }
}