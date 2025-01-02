package dsa.leetcode.fermi_s_paradox;

class Solution {
  int[][] dp;
  int n;

  /**
   * https://leetcode.com/problems/longest-palindromic-subsequence/
   *
   * Dynamic programming approach to find the length of the longest palindromic subsequence.
   * We use a 2D array to store the results of subproblems and utilize recursive logic.
   * 
   * TC: O(n^2) SC: O(n^2)
   * #string #dynamic-programming #medium
   */

  public int longestPalindromeSubseq(String s) {
    n = s.length();
    dp = new int[n][n];
    return helper(0, n - 1, s);
  }

  int helper(int i, int j, String s) {
    if (i > j) return 0; // base case: no subsequence
    else if (i == j) return 1; // base case: single character is a palindrome
    else if (dp[i][j] != 0) return dp[i][j]; // return if already calculated
    
    if (s.charAt(i) == s.charAt(j)) {
      dp[i][j] = 2 + helper(i + 1, j - 1, s);
    } else {
      dp[i][j] = Math.max(helper(i + 1, j, s), helper(i, j - 1, s));
    }
    return dp[i][j];
  }
}