package dsa.leetcode.mithrim_montez;

import java.util.*;

public class LongestSubsequenceWithDecreasingAdjacentDifference {

  /**
   * https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference/
   *
   * Tried the naiive DFS based recursion, but it didn't clear the time constraints.
   * Basically for any index, we can move to any index with a difference less than the current difference.
   * 
   * TC: O(n*300) SC: O(n*300)
   * 
   * Honestly needed a lot of time in a 300x300 dp seek...
   * For each `idx` and `diff`, instead of storing the maximum length of subsequence ending at `idx` with `diff`,
   * we store the maximum length of subsequence ending at `idx`, using `diff` but originating from a previously seen
   * number `next`. Since `next` is limited by 300, we've to traverse only 300 elements for each `idx`, instead of `n`.
   * 
   * TC: O(n*300) SC: O(300*300)
   *
   * #array #dynamic-programming #medium
   */

  public int longestSubsequence(int[] nums) {
    int [][]dp = new int[302][302];
    for(int []d: dp) Arrays.fill(d, -1);
    int res = 0;
    for(int i=nums.length-1; i>=0; i--){   
      int num = nums[i];   
      for(int next=1; next<=300; next++){
        int diff = Math.abs(next-num);
        // we come to `num` only from `next` with 
        // a `diff` and we find the longest subseq by adding one to it
        dp[num][diff] = Math.max(dp[num][diff], dp[next][diff]+1);
      }

      for(int diff=1; diff<=300; diff++){
        // we can come to `num` from any `next` with a `diff` that's either
        // equal to the current diff or less than it
        dp[num][diff] = Math.max(dp[num][diff], dp[num][diff-1]);
      }
    }
    res = Integer.MIN_VALUE;
    for(int i=0; i<=301; i++){
      for(int j=0; j<=301; j++){
        res = Math.max(res, dp[i][j]);
      }
    }
    return res+1;
  }

  /**
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
  public int longestSubsequence(int[] nums) {
    int [][]dp = new int[nums.length][301];
    for(int []d: dp) Arrays.fill(d, -1);
    int res = 0;
    for(int i=0; i<nums.length; i++){
      res = Math.max(res, 1+dfs(i, 300, dp, nums));
    }
    return res;
  }
   */
}