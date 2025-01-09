package dsa.leetcode.mithrim_montez;

public class MinimumTimeToBreakLocks {

  /**
   * https://leetcode.com/problems/minimum-time-to-break-locks-i/description/
   *
   * To solve this problem, we must find the minimum time required to break all locks. Consider each lock breaking as a permutation in time.
   * The energy of the sword increases every minute by the current factor and resets after breaking a lock while the factor increases by k.
   *
   * Time Complexity: O(n!) due to possible permutations
   * Space Complexity: O(2^n * n)
   *
   * #array #dynamic-programming #backtracking #bit-manipulation #depth-first-search #bitmask #medium
   */

  private int dfs(int pos, int x, int[][] dp){
    if(pos >= (1<<N)-1) return 0;
    if(dp[pos][x] != -1) return dp[pos][x];
    int res = Integer.MAX_VALUE;
    for(int i = 0; i < N; i++){
      if((pos & (1<<i)) > 0) continue;
      int moremins = (str[i] + x - 1) / x;
      res = Math.min(res, moremins + dfs(pos | (1<<i), x + k, dp));
    }
    return dp[pos][x] = res;
  }

  private int k, N;
  private int[] str;
}