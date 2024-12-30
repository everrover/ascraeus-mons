package dsa.leetcode.fermi_s_paradox;

import java.util.Arrays;

public class CountWaysToBuildGoodStrings {
  private int[] dp;
  private int low, high, zero, one;
  private static final int MOD = (int)(1e9 + 7);

  /**
   * https://leetcode.com/problems/count-ways-to-build-good-strings/?envType=daily-question&envId=2024-12-30
   *
   * Try adding both ones and zero counts for an index. If the index is within the valid range, count it.
   * 
   * Memoization on top of top-down recursive loop, i.e. `dfs`.
   *
   * TC: O(high) SC: O(high)
   * #dynamic-programming #medium
   */
  private int dfs(int idx) {
    if (high < idx) return 0;
    if (dp[idx] != -1) return dp[idx];
    // Count the current index if it's within the valid range
    int res = (idx >= low && idx <= high) ? 1 : 0;
    // Recursive calculation for adding zeros
    res = (res + dfs(idx + zero)) % MOD;
    // Recursive calculation for adding ones
    res = (res + dfs(idx + one)) % MOD;
    return dp[idx] = res;
  }

  public int countGoodStrings(int low, int high, int zero, int one) {
    this.low = low; this.high = high;
    this.zero = zero; this.one = one;
    dp = new int[high + 1];
    Arrays.fill(dp, -1);
    return dfs(0);
  }
}