package dsa.leetcode.fermi_s_paradox;

import java.util.Arrays;

public class CountWaysToBuildGoodStrings {
  private int[] dp;
  private int low, high, zero, one;
  private static final int MOD = (int)(1e9 + 7);

  /**
   * https://leetcode.com/problems/count-ways-to-build-good-strings/?envType=daily-question&envId=2024-12-30
   *
   * Utilize dynamic programming to count the number of valid strings by adding characters '0' and '1' within a given length range.
   * Calculate the number of good strings with length less or equal to a specified constant, applying group size logic for consecutive zeros and ones.
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