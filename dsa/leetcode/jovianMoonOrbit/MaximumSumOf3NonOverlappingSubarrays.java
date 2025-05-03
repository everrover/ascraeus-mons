package dsa.leetcode.JovianMoonOrbit;

import java.util.*;

public class MaximumSumOf3NonOverlappingSubarrays {
  
  /**
   * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
   * 
   * Basically, we implement 0-1 knapsack problem's implementation with weight of each subarray set to 1.
   * To implement those knapsacks, we use an array to store the sum of each subarray of size k. Sized [k, n-k+1]
   * 
   * Also, could've used using a greedy approach. Since we only need 3 subarrays, 
   * we can just find the max sum subarray combo by finding a middle subarray, with
   * combination of left and right max sum subarray of size k.
   * 
   * TC: O(n) SC: O(n*3)
   * #array #dynamic-programming #hard #0-1-knapsack
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

  private static class T{
    public int max, pos;
    public T(int m, int p){
      max = m;
      pos = p;
    }
  }
  
  public int[] maxSumOfThreeSubarraysForThreeOnly(int[] nums, int k) {
    int n = nums.length;
    int[] prefix = new int[nums.length];
    prefix[0] = nums[0];
    for(int i=1; i<nums.length; i++)
        prefix[i]=prefix[i-1]+nums[i];
    T []prev = new T[nums.length];
    int sum = 0;
    for(int i=0; i<k; i++) sum += nums[i];
    prev[k-1] = new T(sum,0);
    for(int i=k; i<nums.length; i++) {
      sum -= nums[i-k];
      sum += nums[i];
      if(sum > prev[i-1].max) prev[i] = new T(sum, i-(k-1));
      else prev[i] = prev[i-1];
    }
    T []next = new T[nums.length];
    sum = 0;
    for(int i=n-1; i>=n-k; i--) sum += nums[i];
    next[n-k] = new T(sum,n-k);
    for(int i=n-k-1; i>=0; i--) {
      sum -= nums[i+k];
      sum += nums[i];
      if(sum>=next[i+1].max) next[i] = new T(sum,i);
      else next[i] = next[i+1];
    }
    int ressum = 0;
    int[] res = new int[]{-1,-1,-1};
    for(int i=k;i<=n-k*2;i++) {
      int currsum = prev[i-1].max + prefix[i+k-1]-prefix[i-1] + next[i+k].max;
      if(currsum > ressum) {
        ressum = currsum;
        res = new int[]{prev[i-1].pos, i, next[i+k].pos};
      }
    }
    return res;
  }
}