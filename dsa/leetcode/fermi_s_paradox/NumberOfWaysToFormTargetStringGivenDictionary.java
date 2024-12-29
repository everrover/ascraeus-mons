package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/?envType=daily-question&envId=2024-12-29
   *
   * Use frequency array to track available characters and dynamic programming to form target string.
   * 
   * TC: O(n*m*t), where n is the length of words, m is the word length, and t is the target length.
   * SC: O(m*t)
   * #dynamic-programming #string #hard
   */
  
  private static final int MOD = (int)1e9 + 7;

  public int numWays(String[] words, String target) {
    final int M = words[0].length();
    int[][] dp = new int[M + 1][target.length() + 1];
    for (int[] row : dp) Arrays.fill(row, -1);
    return dfs(0, 0, words, target, dp);
  }

  private int dfs(int i, int j, final String[] words, final String target, final int[][] dp) {
    if (j == target.length()) return 1;
    if (i == words[0].length()) return 0;
    if (dp[i][j] != -1) return dp[i][j];

    long cnt = dfs(i + 1, j, words, target, dp) % MOD;
    for (String word : words) {
      if (word.charAt(i) == target.charAt(j)) {
        cnt = (cnt + 1L * dfs(i + 1, j + 1, words, target, dp)) % MOD;
      }
    }

    return dp[i][j] = (int) cnt;
  }
}