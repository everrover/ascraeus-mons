package dsa.leetcode.VallesMarineris;

import java.util.*;

public class SumOfKSubarraysWithLengthAtLeastM {

  /**
   * https://leetcode.com/problems/sum-of-k-subarrays-with-length-at-least-m/description/
   *
   * If we're continuing a streak, we can either continue it, start a new streak or break it. => `cont = 1`
   * If we're starting a new streak, we can either start it or skip the current element. => `cont = 0`
   * Both the cases are handled by the following code. Earlier I had placed a check on 
   * `cont`
   * ```java
   * if(cont == 0) {
   *   res = max(dfs(idx+1, kdx, 0), res); // skip current
   *   // start new streak
   *   if(idx+m-1 < nums.length) { // with atleast `m` elements
   *     int nextres = dfs(idx+m, kdx-1, 0);
   *     if(nextres != S) res = max(nextres + p[idx+m-1+1]-p[idx], res);
   *   }
   * } else {
   *   res = max(dfs(idx+1, kdx, 0), res); // skip current
   *   res = max(nums[idx] + dfs(idx+1, kdx, 1), res); // continue streak
   *   // start new streak
   *   if(idx+m-1 < nums.length) { // with atleast `m` elements
   *     int nextres = dfs(idx+m, kdx-1, 0);
   *     if(nextres != S) res = max(nextres + p[idx+m-1+1]-p[idx], res);
   *   }
   * }
   * 
   * ```
   *
   * TC: O(n*k) SC: O(n*k)
   * #dynamic-programming #prefix-sum #medium
   */

  // private static final int S = Integer.MIN_VALUE;
  private static final int S = -5*(int)1e8;
  private int []p = new int[1];
  private int []nums = new int[1];
  private int [][][]dp = new int[1][1][1];
  private int m = -1;
  public int maxSum(int[] nums, int k, int m) {
    if(k*m > nums.length) return 0;

    p = new int[nums.length+1];
    for(int i=1; i<=nums.length; i++) p[i] = p[i-1]+nums[i-1];

    dp = new int[2][nums.length][k+1];
    for(int [][]dd: dp) {
      for(int []d: dd) {
        Arrays.fill(d, S);
        d[0] = 0;
      }
    }

    this.nums = nums;
    this.m = m;

    return dfs(0, k, 0);
  }

  private int dfs(int idx, int kdx, int cont){
    if(idx == nums.length && kdx > 0) return S;
    else if(idx == nums.length || kdx == 0) return 0;
    else if(dp[cont][idx][kdx] != S) return dp[cont][idx][kdx];

    // start at idx
    int res = S;
    // breaking current streak @ cont = 0, starting new by skipping current
    if(cont == 0) res = dfs(idx+1, kdx, 0);

    // try continuing prev streak
    res = Math.max(res, nums[idx] + dfs(idx+1, kdx, 1));

    // start new streak with atleast `m` elements
    if(idx+m-1 < nums.length){
      int nextres = dfs(idx+m, kdx-1, 0);
      if(nextres != S) res = Math.max(res, nextres + p[idx+m-1+1]-p[idx]);
    }

    return dp[cont][idx][kdx]=res;
  }

  // older version
  // private static final int S = -5*(int)1e8;
  // private int []p = new int[1];
  // public int maxSum(int[] nums, int k, int m) {
  //   if(k*m > nums.length) return 0;
  //   p = new int[nums.length+1];
  //   for(int i=1; i<=nums.length; i++) p[i] = p[i-1]+nums[i-1];
  //   int [][]dp = new int[nums.length][k+1];
  //   for(int []d: dp) {
  //     Arrays.fill(d, S);
  //     d[0] = 0;
  //   }
  //   return dfs(0, k, m, nums, dp);
  // }

  // private int dfs(int idx, int kdx, final int m, final int[] nums, final int[][]dp){
  //   if(kdx == 0) return 0;
  //   final int limit = nums.length-kdx*m;
  //   if(idx > limit) return S;
  //   else if(dp[idx][kdx] != S) return dp[idx][kdx];

  //   // start at idx
  //   int res = dfs(idx+1, kdx, m, nums, dp);
  //   // expansion of range
  //   for(int i=idx+m-1; i<limit+m; i++){
  //     int ires = dfs(i+1, kdx-1, m, nums, dp);
  //     if(ires != S) res = Math.max(res, p[i+1]-p[idx]+ires);
  //   }

  //   return dp[idx][kdx]=res;
  // }
}