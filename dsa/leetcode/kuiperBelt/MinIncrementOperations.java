package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class MinIncrementOperations {
  /**
   * https://leetcode.com/problems/minimum-increment-operations-to-make-array-beautiful/
   * 
   * Recursion equation:
   * ```
   * dfs(idx) = min(
   *  k-nums[idx]+dfs(idx+1),
   *  k-nums[idx]+dfs(idx+2),
   *  k-nums[idx]+dfs(idx+3)
   * )
   * ```
   * 
   * Applied memoization on top of recursion.
   * 
   * #array #greedy #medium #dynamic-programming
   */
  public long minIncrementOperations(int[] nums, int k) {
    long []dp = new long[nums.length];
    Arrays.fill(dp, -1);
    dfs(0, dp, nums, k);
    return Math.min(Math.min(dp[0], dp[1]), dp[2]);
  }

  private long dfs(int idx, long []dp, int []nums, final int k){
    if(idx>=nums.length) return 0L;
    if(dp[idx] != -1) return dp[idx];
    long val = Math.max(k-nums[idx], 0);
//    System.out.println(idx+":"+res);
    return dp[idx] = Math.min(
      Math.min(
        val+dfs(idx+1,dp,nums,k), 
        val+dfs(idx+2,dp,nums,k)
      ), 
      val+dfs(idx+3,dp,nums,k)
    );
  }
}
