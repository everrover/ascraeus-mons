package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/uncrossed-lines/
   *
   * Uses dynamic programming to find the maximum number of uncrossed lines that can be drawn
   * between two arrays. The recurrence checks pairs of numbers
   * and continues if they are equal.
   *
   * TC: O(n*m) SC: O(n*m) where n and m are the lengths of nums1 and nums2 respectively
   * #array #dynamic-programming #medium
   */
  
  public int maxUncrossedLines(int[] nums1, int[] nums2) {
    int dp[][] = new int[nums1.length][nums2.length];
    for (int[] d : dp) Arrays.fill(d, -1);
    return dfs(nums1, nums2, 0, 0, dp);
  }

  private int dfs(int[] nums1, int[] nums2, int i, int j, int[][] dp) {
    if (i == nums1.length || j == nums2.length) return 0;
    if (dp[i][j] != -1) return dp[i][j];
    if (nums1[i] == nums2[j]) return dp[i][j] = 1 + dfs(nums1, nums2, i + 1, j + 1, dp);
    return dp[i][j] = Math.max(
      dfs(nums1, nums2, i + 1, j, dp),
      dfs(nums1, nums2, i, j + 1, dp)
    );
  }
}