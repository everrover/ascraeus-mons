package dsa.leetcode.RhoCassiopeiae;

public class TargetSum {

  /**
   * https://leetcode.com/problems/target-sum/submissions/
   *
   * The problem is reduced to finding two subsets of given array with a given difference. This is solved using dynamic programming where 
   * dp[i] represents the number of ways to achieve the target sum using the first i numbers.
   *
   * TC: O(n * sum) SC: O(sum)
   * #array #dynamic-programming #backtracking #medium
   */  

  public int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for(int num: nums) sum += Math.abs(num);
    int []dp = new int[2*sum+1];
    dp[sum+nums[0]] = 1;
    dp[sum-nums[0]] += 1;

    for(int i=1; i<nums.length; i++){
      int []next = new int[2*sum+1];
      for(int s = -sum; s<=sum; s++){
        if(dp[sum+s] > 0){
          next[sum+s+nums[i]] += dp[s+sum];
          next[sum+s-nums[i]] += dp[s+sum];
        }
      }
      dp = next;
    }
    return Math.abs(target) > sum? 0 : dp[target+sum];
  }
}