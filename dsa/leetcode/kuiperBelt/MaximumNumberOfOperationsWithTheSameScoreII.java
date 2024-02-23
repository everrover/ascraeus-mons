package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class MaximumNumberOfOperationsWithTheSameScoreII {

  /**
   * https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-ii/
   * The approach is to use dynamic programming with memoization to keep track of operations 
   * that yield the same score when deleting elements from the array.
   *
   * TC: O(n^2) SC: O(n^2)
   * #array #dynamic-programming #memoization #medium
   */

  public int maxOperations(int[] nums) {
    int i=0, j=nums.length-1, res = 0;
    if(nums.length <= 1) return 0;
    int [][]memo = new int[nums.length][nums.length];
    for(int []m: memo) Arrays.fill(m, -1);
    res = 1+dfs(1, nums.length-2, nums[0]+nums[nums.length-1], memo, nums);
    // for(int []m: memo) Arrays.fill(m, -1); # Why's this not needed, but it worked...
    res = Math.max(res, 1+dfs(2, nums.length-1, nums[0]+nums[1], memo, nums));
    // for(int []m: memo) Arrays.fill(m, -1);
    res = Math.max(res, 1+dfs(0, nums.length-3, nums[nums.length-2]+nums[nums.length-1], memo, nums));
    return res;
  }
  
  private int dfs(int i, int j, int k, int [][]memo, int []nums){
    if(i>=j) return 0;
    if(memo[i][j] != -1) return memo[i][j];
    int res = 0;
    if(nums[i]+nums[j] == k) res = Math.max(res, 1+dfs(i+1, j-1, k, memo, nums));
    if(nums[i]+nums[i+1] == k) res = Math.max(res, 1+dfs(i+2, j, k, memo, nums));
    if(nums[j-1]+nums[j] == k) res = Math.max(res, 1+dfs(i, j-2, k, memo, nums));
    return memo[i][j] = res;
  }
}