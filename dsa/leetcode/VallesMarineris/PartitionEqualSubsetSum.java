package dsa.leetcode.VallesMarineris;

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

  private int dfs(int idx, int sum, final int hs, final int []nums, final int [][]dp) {
    // If we reach the end of the array, check if subset sum matches
    if(idx == nums.length) return hs==sum?1:0;
    // If current sum exceeds half-sum, return false
    if(sum > hs) return 0;
    // Return cached result if calculated
    if(dp[idx][sum] != -1) return dp[idx][sum];
    // Explore including nums[idx] in subset or not
    int t = dfs(idx+1, sum+nums[idx], hs, nums, dp);
    int nt = dfs(idx+1, sum, hs, nums, dp);
    // Cache and return if any configuration gives a valid partition
    return dp[idx][sum] = (1==t || 1==nt)?1:0;
  }
}