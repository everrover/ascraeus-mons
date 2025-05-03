package dsa.leetcode.KuiperBelt;

// imports here (if necessary)

public class MinimumNumberOfFlipsToMakeBinaryGridPalindromic {
  /**
   * https://leetcode.com/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-i/
   *
   * The algorithm counts the number of mismatched elements in rows and columns by comparing symmetric elements
   * and finds the minimum flips required to make either all rows or all columns palindromic.
   *
   * TC: O(m * n/2)
   * SC: O(1)
   * #grid #matrix #palindrome #greedy #medium #contest
   */

  public int minFlips(int[][] grid) {
    final int m = grid.length, n = grid[0].length;
    // check row transitions
    int cnt = 0;
    for(int i=0; i<m; i++){
      for(int j=0; j<n/2; j++){
        if(grid[i][j] != grid[i][n-j-1]) cnt++;
      }
    }
    // check col transitions
    int res = cnt; cnt = 0;
    for(int i=0; i<m/2; i++){
      for(int j=0; j<n; j++){
        if(grid[i][j] != grid[m-i-1][j]) cnt++;
      }
    }
    return Math.min(res, cnt);
  }
}