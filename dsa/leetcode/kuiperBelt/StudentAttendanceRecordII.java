package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

public class StudentAttendanceRecordII {

  /**
   * https://leetcode.com/problems/student-attendance-record-ii/
   *
   * Simply applied the computations needed to get the result. 
   * dfs(n, a, l) = dfs(n-1, a, 2)  (present on day n)
   *                + dfs(n-1, a-1, 2), if a > 0 (absent on day n)
   *                + dfs(n-1, a, l-1), if l > 0 (late attendance)
   * And memoization on top of it in a top-down manner.
   * Can use bottom-up as well.
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
  /**
  public int checkRecord(int n) {
    int [][][]memo = new int[n+1][2][3];
    for(int [][]me: memo) for(int []m: me) Arrays.fill(m, -1);
    memo[0] = new int[][]{{1,1,1},{1,1,1}};
    for(int i=1; i<=n; i++){
      for(int j=0; j<2; j++){
        for(int k=0; k<3; k++){
          int res = memo[i-1][j][2]%MOD;
          if(j>0) res = (res + memo[i-1][j-1][2])%MOD;
          if(k>0) res = (res + memo[i-1][j][k-1])%MOD;
          memo[i][j][k] = res;
        }
      }
    }
    return memo[n][1][2];
  }
   */
  /*
  
  private int dfs(int ab, int lt, int day, final int n, final int[][][]dp){
    if(day == n) return 1;
    if(dp[ab][lt][day] != -1) return dp[ab][lt][day];
    int res = 0;
    if(ab < 1) res = (res + dfs(ab+1, 0, day+1, n, dp))%MOD;
    if(lt < 2) res = (res + dfs(ab, lt+1, day+1, n, dp))%MOD;
    res = (res + dfs(ab, 0, day+1, n, dp))%MOD;
    return dp[ab][lt][day] = res;
  }
  public int checkRecord(int n) {
    int [][][]dp = new int[2][3][n];
    for(int [][]dd: dp) for(int []d: dd) Arrays.fill(d, -1);
    return dfs(0, 0, 0, n, dp);
  }
   */
}