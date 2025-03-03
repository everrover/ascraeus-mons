package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class LongestPalindromicSubsequenceAfterAtMostKOperations {

  /**
   * https://leetcode.com/problems/longest-palindromic-subsequence-after-at-most-k-operations/description/
   * 
   * To find the longest palindromic subsequence after at most k operations, we use dynamic programming. We define dp[i][j][k] as the length of the longest palindromic subsequence in the substring s[i..j] with at most k operations allowed.
   * If the characters at the current extremities match, add to the length if possible: dp[i][j][k] = max(dp[i + 1][j][k], dp[i][j - 1][k], dp[i + 1][j - 1][k - dist(s[i], s[j])] + 2), where dist(x, y) is the minimum cyclic distance between x and y.
   * 
   * TC: O(n^2 * k), SC: O(n^2 * k)
   * #dp #palindrome #dynamic-programming #medium
   */

  public int longestPalindromicSubsequence(String s, int k) {
    int[][][] dp = new int[s.length()][s.length()][k+1];
    for(int[][] dd: dp) for(int[] d: dd) Arrays.fill(d, -1);
    return dfs(0, s.length()-1, k, s.toCharArray(), k, dp);
  }

  private int dfs(int idx, int jdx, int kdx, char[] chs, int k, int[][][] dp) {
    if(idx > jdx || kdx < 0) return 0;
    else if(idx == jdx) return dp[idx][jdx][kdx] = 1;
    else if(dp[idx][jdx][kdx] != -1) return dp[idx][jdx][kdx];
    
    int res = Math.max(dfs(idx+1, jdx, kdx, chs, k, dp), dfs(idx, jdx-1, kdx, chs, k, dp));
    int dist = dist(chs[idx], chs[jdx]);
    // if(chs[idx] == chs[jdx]) res = Math.max(res, dfs(idx+1, jdx-1, kdx, chs, k, dp) + 2);
    // else
    if(dist <= kdx)
      res = Math.max(res, 2 + dfs(idx+1, jdx-1, kdx-dist, chs, k, dp));
    return dp[idx][jdx][kdx] = res;
  }

  private int dist(char x, char y) {
    return Math.min(Math.abs(x - y), 26 - Math.abs(x - y));
  }
}