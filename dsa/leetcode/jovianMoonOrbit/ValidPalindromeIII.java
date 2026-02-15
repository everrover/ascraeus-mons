package dsa.leetcode.JovianMoonOrbit;

import java.util.Arrays;

public class ValidPalindromeIII {

  /**
   * https://leetcode.com/problems/valid-palindrome-iii/submissions/
   * The problem is equivalent to finding the longest palindromic subsequence. 
   * 
   * Two approaches:
   * - Selection of selected palindrome characters with deletion
   * - Selection of minimum deleted characters only
   * 
   * The prior approach has a time complexity of O(n^2*k) and space complexity of O(n^2*k)
   * Hence didn't work.
   * 
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

  /** // Selection of selected palindrome characters
    public boolean isValidPalindrome(String s, int k) {
      int n = s.length();
      int [][][]memo = new int[n][n][k+1];
      for(int [][]mem: memo){
        for(int []me: mem) Arrays.fill(me, -1);
      }
      return dfs(0, n-1, k, memo, s.toCharArray())>=(n-k);
    }
    private int dfs(int a, int b, int k, int [][][]memo, char[] chs){
      if(a>b || k<0) return 0;
      if(memo[a][b][k] != -1) return memo[a][b][k];
      int res = 0;
      if(a == b) res = 1;
      else{
        res = Math.max(
          Math.max(dfs(a, b-1, k-1, memo, chs), dfs(a+1, b, k-1, memo, chs)),
          chs[a]==chs[b]?(dfs(a+1, b-1, k, memo, chs)+2):0
        );
      }
      return memo[a][b][k] = res;
    }
   */
}