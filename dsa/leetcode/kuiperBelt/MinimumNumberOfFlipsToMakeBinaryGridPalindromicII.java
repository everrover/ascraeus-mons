package dsa.leetcode.KuiperBelt;

// imports if necessary

public class MinimumNumberOfFlipsToMakeBinaryGridPalindromicII {

  /**
   * https://leetcode.com/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/
   * 
   * To make all rows and columns palindromic, we need to ensure that for each element (x, y), 
   * its corresponding mirror positions (m-1-x, y), (m-1-x, n-1-y), and (x, n-1-y) should be the same. Also that way count-of-ones%4 == 0
   *
   * Additionally, handle the middle row/column if the number of rows/columns is odd. Count the adjustments required.
   *
   * TC: O(m * n / 2) SC: O(1)
   * #matrix #palindrome #greedy #medium #contest
   */

  int m, n;

  public int minFlips(int[][] grid) {
    m = grid.length; n = grid[0].length;
    final int mm = m % 2 == 0 ? 0 : 1, nm = n % 2 == 0 ? 0 : 1;
    int cntmid = 0;
    int cnt = 0, ones = 0;
    if (mm != 0) {
      for (int i = 0; i < n / 2; i++) {
        if (grid[m / 2][i] != grid[m / 2][n - i - 1]) { cntmid++; }
        else if (grid[m / 2][i] == 1) ones += 2;
      }
    }
    if (nm != 0) {
      for (int i = 0; i < m / 2; i++) {
        if (grid[i][n / 2] != grid[m - i - 1][n / 2]) { cntmid++; }
        else if (grid[i][n / 2] == 1) ones += 2;
      }
    }
    if (mm != 0 || nm != 0) {
      // count of ones is always even
      if (ones % 4 != 0 && cntmid == 0) cntmid += 2; // if even one pair is available, we can make the pair-elements 0/1 and hence make adjustments for cnt-of-ones%4 == 0
      if (mm != 0 && nm != 0 && grid[m / 2][n / 2] == 1) cntmid++; // if both are odd, then the middle element should be 0
    }
    for (int i = 0; i < m / 2; i++) {
      for (int j = 0; j < n / 2; j++) {
        int onec = 0;
        onec += grid[i][j];
        onec += grid[i][n - j - 1];
        onec += grid[m - i - 1][j];
        onec += grid[m - i - 1][n - j - 1];
        cnt += Math.min(onec, 4 - onec);
      }
    }
    return cnt + cntmid;
  }
}