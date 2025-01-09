package dsa.leetcode.mithrim_montez;

import java.util.Arrays;
import java.util.List;

public class MinimumTimeToBreakLocks {

  /**
   * https://leetcode.com/problems/minimum-time-to-break-locks-i/description/
   *
   * APPlied DFS[Backtracking] with memoization to find the minimum time to break all locks.
   *
   * Time Complexity: O(n!) due to possible permutations
   * Space Complexity: O(2^n * n)
   *
   * #array #dynamic-programming #backtracking #bit-manipulation #depth-first-search #bitmask #medium
   */

  private int[] str;
  private int k, N;
  private int dfs(int pos, int x, int [][]dp){
    if(pos >= (1<<N)-1) return 0;
    if(dp[pos][x] != -1) return dp[pos][x];
    int res = Integer.MAX_VALUE;
    for(int i=0; i<N; i++){
      if((pos&(1<<i)) > 0) continue;
      int moremins = (str[i]+x-1)/x;
      res = Math.min(res, moremins + dfs(pos | (1<<i), x+k, dp));
    }

    return dp[pos][x] = res;
  }
  public int findMinimumTime(List<Integer> strength, int k) {
    int []str = new int[strength.size()];
    int max = 0;
    for(int i=0; i<strength.size(); i++){
      str[i] = strength.get(i);
      max = Math.max(max, str[i]);
    }
    this.k = k;
    this.str = str;
    this.N = str.length;
    int [][]dp = new int[(int)Math.pow(2,N)][max+2*k+1];
    for(int []d: dp) Arrays.fill(d, -1);
    int res = dfs(0, 1, dp);
    return res;
  }
}