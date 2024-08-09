package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class StudentAttendanceRecordII {

  /**
   * https://leetcode.com/problems/student-attendance-record-ii/
   *
   * An attendance record can be represented as a string.
   * 'A' means Absent, 'L' means Late, 'P' means Present.
   * A student is eligible for an award if there are fewer than 2 'A'
   * and never three or more consecutive 'L'.
   * Dynamically calculate combinations.
   *
   * TC: O(n) SC: O(n)
   * #dp #string #hard
   */

  private static final int MOD = (int)1e9 + 7;

  public int checkRecord(int n) {
    int[][][] memo = new int[n + 1][2][3];
    for (int[][] me: memo) for (int[] m: me) Arrays.fill(m, -1);
    memo[0] = new int[][]{{1, 1, 1}, {1, 1, 1}};
    dfs(n, 1, 2, memo);
    return memo[n][1][2];
  }

  public int dfs(int num, int a, int l, int[][][] memo) {
    if (memo[num][a][l] != -1) return memo[num][a][l];
    int res = dfs(num - 1, a, 2, memo) % MOD;
    if (a > 0) res = (res + dfs(num - 1, a - 1, 2, memo)) % MOD;
    if (l > 0) res = (res + dfs(num - 1, a, l - 1, memo)) % MOD;
    return memo[num][a][l] = res;
  }
}