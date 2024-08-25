package dsa.leetcode.jovianMoonOrbit;

import java.util.Arrays;

public class ValidPalindromeIII {

  /**
   * https://leetcode.com/problems/valid-palindrome-iii/submissions/
   * 
   * The problem is equivalent to finding the longest palindromic subsequence. 
   * If the length of the longest palindromic subsequence in the string is at least N-K, 
   * where N is the length of the string, then the string can be a k-palindrome.
   * 
   * TC: O(n^2) SC: O(n^2)
   * #dynamic-programming #string #hard 
   */

  public boolean isValidPalindrome(String s, int k) {
    int n = s.length();
    int [][]memo = new int[n][n];
    for(int []mem: memo){
      Arrays.fill(mem, -1);
    }
    return dfs(0, n-1, memo, s.toCharArray()) <= k;
  }

  private int dfs(int a, int b, int [][]memo, char[] chs) {
    if (a > b) return 0;
    if (memo[a][b] != -1) return memo[a][b];
    int res = 1 + Math.min(dfs(a, b-1, memo, chs), dfs(a+1, b, memo, chs));
    if (chs[a] == chs[b]) res = Math.min(res, dfs(a+1, b-1, memo, chs));
    return memo[a][b] = res;
  }
}