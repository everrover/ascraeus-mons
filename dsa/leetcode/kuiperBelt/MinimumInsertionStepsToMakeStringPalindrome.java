package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 *
 * It was a curious observation. I was thinking of LCS solution saw that i can check for equivalency on str[idx] and
 * str[str.length-jdx-1] to see the number of matching characters.
 *
 * TC: O(n^2) SC: O(n^2)
 * #dynamic-programming #string #palindrome #hard #lcs #dfs
 */

public class MinimumInsertionStepsToMakeStringPalindrome {

  public int minInsertions(String s) {
    int n = s.length();
    int [][]dp =  new int[n+1][n+1];
    // Initialize the dp array
    for(int []d: dp) Arrays.fill(d, -1);
    // Perform DFS to fill dp array
    dfs(0, 0, s.toCharArray(), dp);
    // Answer is string length minus longest palindromic subsequence
    return n - dp[0][0];
  }

  private int dfs(int idx, int jdx, char []str, int [][]dp){
    if(idx >= str.length || jdx >= str.length) return 0;
    // Return calculated result to avoid recomputation
    if(dp[idx][jdx] != -1) return dp[idx][jdx];
    int res = 0;
    // Check and choose the maximum length subsequence
    if(str[idx] == str[str.length-jdx-1]) res = dfs(idx+1, jdx+1, str, dp)+1;
    else res= Integer.max(dfs(idx+1,jdx, str, dp), dfs(idx,jdx+1, str, dp));
    dp[idx][jdx] = res;
    return res;
  }
}