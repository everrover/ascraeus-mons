package dsa.leetcode.JupitersGreatStorm;

import java.util.HashSet;
import java.util.Set;

class FindXValueOfArrayI {

  /**
   * https://leetcode.com/problems/find-x-value-of-array-i/description/
   *
   * Use dynamic programming to calculate subarrays. Define dp[i][r] as 
   * the count of subarrays ending at index i whose product modulo k equals r.
   * Compute dp[i][r] for each index i in nums and sum over all indices to
   * get the final counts for each remainder.
   *
   * TC: O(n * k) SC: O(n * k)
   * #array #math #dynamic-programming #medium
   */

   public long[] resultArray(int[] nums, int k) {
    Set<Long> ps = new HashSet<>();
    long[] res = new long[k];
    for(int i=0; i<nums.length; i++) nums[i] %= k;
    Long [][]dp;
    for(int j=0; j<k; j++){
      dp = new Long[nums.length][k+1];
      res[j] = dfs(0, j, -1, k, nums, dp);
    }
    return res;
  }

  private long dfs(int idx, int req, int prod, final int k, final int []nums, final Long[][] dp){
    if(idx == nums.length) return 0L;
    if(dp[idx][prod+1] != null) return dp[idx][prod+1];
    long take = 0, notTake = 0, count = 0;
    if(prod == -1){ // product seq not started
      if(nums[idx] == req) count=1;
      take = count + dfs(idx+1, req, nums[idx], k, nums, dp);
      notTake = dfs(idx+1, req, -1, k, nums, dp);
    }else{ // product seq already started
      int newProd = (prod*nums[idx]) % k;
      if(newProd == req) count=1;
      take = count + dfs(idx+1, req, newProd, k, nums, dp);
    }
    return dp[idx][prod+1] = take+notTake;
  }
}