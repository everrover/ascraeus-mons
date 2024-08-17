package dsa.leetcode.jovianMoonOrbit;

import java.util.Arrays;

public class MaximumValueSumByPlacingThreeRooksII {

  /**
   * https://leetcode.com/problems/maximum-value-sum-by-placing-three-rooks-ii/
   *
   * The idea is to sort the rows of the board in descending order based on cell values,
   * and then use recursive depth-first search (DFS) to attempt to place the three rooks
   * in non-attacking positions within the top three rows.
   * 
   * We can use segment-tree here as well. Or a PQ. To find 2nd and third max.
   * 
   * Only difference in /maximum-value-sum-by-placing-three-rooks-ii and /maximum-value-sum-by-placing-three-rooks-i is
   * the constraints. 500/100 max board size respectively.
   *
   * TC: O(m * n * log(n)) SC: O(m * n)
   * #chess #DFS #dynamic-programming #hard #greedy #sorting #segment-tree #prefix-sum
   */

  private final static int F = 0, S = 1;
  private final static long B = -1 * (long)(1e18);

  int m, n;

  public long maximumValueSum(int[][] board) {
    m = board.length; n = board[0].length;
    int[][][] elems = new int[m][n][2];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        elems[i][j][0] = board[i][j];
        elems[i][j][1] = j;
      }
    }

    for (int i = 0; i < m; i++) {
      Arrays.sort(elems[i], (a, b) -> Integer.compare(b[F], a[F]));
    }
    Arrays.sort(elems, (a, b) -> Integer.compare(b[0][F], a[0][F]));

    long[][] dp = new long[m + 1][n + 1];
    long res = B;

    for (int idx = 0; idx < n; idx++) {
      for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
      res = Math.max(res, elems[0][idx][F] + dfs(1, elems[0][idx][S], -1, elems, dp));
    }

    return res;
  }

  private long dfs(int idx, int rdx2, int rdx1, int[][][] elems, long[][] dp) {
    if (idx >= m) return B;
    if (dp[idx][rdx1 + 1] != -1) return dp[idx][rdx1 + 1];

    long res = dfs(idx + 1, rdx2, rdx1, elems, dp); // pick nth from this layer
    for (int i = 0; i < n; i++) {
      if (elems[idx][i][S] == rdx1 || elems[idx][i][S] == rdx2) continue;
      if (rdx1 != -1) res = Math.max(res, elems[idx][i][F]);
      else {
        long val = elems[idx][i][F] + dfs(idx + 1, rdx2, elems[idx][i][S], elems, dp);
        res = Math.max(res, val);
      }
    }
    return dp[idx][rdx1 + 1] = res;
  }
}