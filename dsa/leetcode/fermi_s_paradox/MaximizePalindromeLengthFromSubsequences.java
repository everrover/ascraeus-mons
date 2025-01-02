package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class MaximizePalindromeLengthFromSubsequences {
  /**
   * https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
   *
   * To solve the problem, we utilize dynamic programming by checking each pair of characters from word1 and word2
   * and determine if a palindrome can start and end with those characters. We employ DFS and memoization for
   * calculating the longest palindromic subsequence. This ensures subsequences are composed of non-empty parts
   * from each word.
   *
   * TC: O(n*m) SC: O((n+m)^2)
   * #string #dynamic-programming #hard
   */

  private int dfs(int i, int j, final char[] chs, final int[][] dp) {
    if (i > j) return 0;
    else if (i == j) return 1;
    else if (dp[i][j] != 0) return dp[i][j];

    if (chs[i] == chs[j]) {
      dp[i][j] = 2 + dfs(i + 1, j - 1, chs, dp);
    } else {
      dp[i][j] = Math.max(dfs(i + 1, j, chs, dp), dfs(i, j - 1, chs, dp));
    }
    return dp[i][j];
  }

  public int longestPalindrome(String word1, String word2) {
    int wl = word1.length();
    char[] chs = (word1 + word2).toCharArray();
    int[][] dp = new int[chs.length][chs.length];
    int res = -1;
    for (int i = 0; i < wl; i++) {
      for (int j = wl; j < chs.length; j++) {
        if (chs[i] == chs[j]) {
          res = Math.max(res, dfs(i, j, chs, dp));
        }
      }
    }
    return res == -1 ? 0 : res;
  }
}