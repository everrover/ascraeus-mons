package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class FindMinimumCostToRemoveArrayElements {

    /**
     * https://leetcode.com/problems/find-minimum-cost-to-remove-array-elements/description/
     *
     * The problem is approached using dynamic programming. 
     * 
     * dfs(idx, jdx) = max-possible-sum of the subarray starting at idx and possible second element at jdx.
     * Essentially idx is the possible first sub-array element that hasn't been used for a given jdx...
     *
     * TC: O(n^2) SC: O(n^2)
     * #array #dynamic-programming #medium
     */

    public int minCost(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int[] d : dp) Arrays.fill(d, -1);
        return dfs(0, 1, nums, dp);
    }

    private int dfs(int idx, int jdx, int []nums, int [][]dp){
        if(jdx == nums.length) return nums[idx];
        if(jdx == nums.length-1) return Math.max(nums[idx], nums[jdx]);
        if(dp[idx][jdx] != -1) return dp[idx][jdx];
        int res = Math.max(nums[idx], nums[jdx])+dfs(jdx+1, jdx+2, nums, dp);
        res = Math.min(
          res,
          Math.max(nums[idx], nums[jdx+1])+dfs(jdx, jdx+2, nums, dp)
        );
        res = Math.min(
          res,
          Math.max(nums[jdx], nums[jdx+1])+dfs(idx, jdx+2, nums, dp)
        );
        return dp[idx][jdx] = res;
    }
}