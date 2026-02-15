package dsa.leetcode.fermi_s_paradox;

class LongestPalindromicSubsequence {
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
    return dfs(0, n - 1, s);
  }

  private int dfs(int i, int j, String s) {
    if (i > j) return 0; // base case: no subsequence
    else if (i == j) return 1; // base case: single character is a palindrome
    else if (dp[i][j] != 0) return dp[i][j]; // return if already calculated
    
    if (s.charAt(i) == s.charAt(j)) {
      dp[i][j] = 2 + dfs(i + 1, j - 1, s);
    } else {
      dp[i][j] = Math.max(dfs(i + 1, j, s), dfs(i, j - 1, s));
    }
    return dp[i][j];
  }
}