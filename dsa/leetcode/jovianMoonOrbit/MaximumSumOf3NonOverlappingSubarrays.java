package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class Solution {
  
  /**
   * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
   *
   * The problem is solved using dynamic programming. The main idea is to keep
   * track of possible sums of subarrays and recursively determine the maximum
   * sum of three non-overlapping subarrays using two main functions: 'dfs' for
   * calculating maximum sums and 'dfstrav' for traversing and finding the indices
   * corresponding to these sums. Dynamic programming is used to prevent
   * recalculations by storing intermediate results.
   * 
   * TC: O(n) SC: O(n)
   * #array #dynamic-programming #hard
   */

  private int dfs(int i, int n, final int k, int []arr, int [][]dp){
    if(n == 0) return 0;
    if(i >= arr.length){
      if(n != 0) return Integer.MIN_VALUE;
      else return 0;
    }else{
      if(dp[i][n] != -1) return dp[i][n];
      else{
        int take = dfs(i+1, n, k, arr, dp);
        int donttake = arr[i]+dfs(i+k, n-1, k, arr, dp);
        return dp[i][n] = Math.max(take, donttake);
      }
    }
  }

  int[] res = new int[3];
  int residx = 0;

  void dfstrav(int i, int n, final int k, int[] arr, int[][] dp) {
    if(0 == n) return;
    else {
      int take = arr[i]+dfs(i+k, n-1, k, arr, dp);
      int nottake = dfs(i+1, n, k, arr, dp);
      if(take >= nottake) {
        res[residx++] = i;
        dfstrav(i+k, n-1, k, arr, dp);
      }
      else dfstrav(i+1, n, k, arr, dp);
    }
  }

  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    int []arr = new int[nums.length-k+1];
    int sum=0, j=0;
    
    // Calculate initial subarray sum and populate arr
    for(int i=0; i<k; i++) sum += nums[i];
    arr[j++] = sum;
    
    // Update arr with sliding window sum
    for(int i=k; i<nums.length; i++){
      sum -= nums[i-k];
      sum += nums[i];
      arr[j++] = sum;
    }
    
    // Initialize dp array
    int [][]dp = new int[nums.length][4];
    for(int []d: dp) Arrays.fill(d, -1);
    
    // Calculate max sums using dfs
    dfs(0, 3, k, arr, dp);
    
    // Traverse to find indices
    dfstrav(0, 3, k, arr, dp);
    
    return res;
  }
}