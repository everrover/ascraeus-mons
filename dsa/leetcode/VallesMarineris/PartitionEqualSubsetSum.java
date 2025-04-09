package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

  /**
   * https://leetcode.com/problems/partition-equal-subset-sum/?envType=daily-question&envId=2025-04-07
   * 
   * The goal is to partition the array into two subsets with equal sum. This can be
   * reduced to a knapsack problem where we check if there is a subset with the sum
   * equal to half of the total array sum. Dynamic programming is used to explore subset sums.
   * 
   * TC: O(n * sum) SC: O(n * sum)
   * #array #dynamic-programming #medium
   */

  public boolean canPartition(int[] nums) {
    int sum = 0;
    for(int num: nums) sum+=num;
    if(sum%2 == 1) return false;
    int [][]dp = new int[nums.length][sum/2+1];
    for(int []dd: dp) Arrays.fill(dd, -1);
    return dfs(0, 0, sum/2, nums, dp) == 1;
  }

  private int dfs(int idx, int sum, final int hs, final int []nums, final int [][]dp){
    if(sum > hs) return 0;
    if(idx == nums.length) return hs==sum?1:0;
    if(dp[idx][sum] != -1) return dp[idx][sum];
    int t = dfs(idx+1, sum+nums[idx], hs, nums, dp);
    int nt = dfs(idx+1, sum, hs, nums, dp);
    return dp[idx][sum] = (1==t || 1==nt)?1:0;
  }
}