package dsa.leetcode.VallesMarineris;

import java.util.*;

public class FindMaximumRemovalsFromSourceString {

  /**
   * https://leetcode.com/problems/find-maximum-removals-from-source-string/description/
   *
   * Use dynamic programming. At each index in targetIndices,
   * make the choice to remove or not remove the character.
   *
   * TC: O(n * m) SC: O(n * m)
   * #array #hash-table #two-pointers #string #dynamic-programming #medium
   */

  public int findMaximumRemovals(String source, String pattern, int[] targetIndices) {
    char[] s = source.toCharArray();
    char[] p = pattern.toCharArray();
    Set<Integer> set = new HashSet<>();
    for (int idx : targetIndices) {
      set.add(idx);
    }
    int[][] dp = new int[source.length() + 1][pattern.length() + 1];
    for (int[] dd : dp) Arrays.fill(dd, -100001);
    return dfs(0, 0, dp, s, p, set);
  }

  private int dfs(int idx, int jdx, final int[][] dp, final char[] s, final char[] p, final Set<Integer> set) {
    if (idx >= s.length) return jdx >= p.length ? 0 : -100000;
    else if (dp[idx][jdx] != -100001) return dp[idx][jdx];

    int res = jdx >= p.length ? 0 : -2;
    if (set.contains(idx)) {
      res = Math.max(res, dfs(idx + 1, jdx, dp, s, p, set));
    }
    if (jdx < p.length && s[idx] == p[jdx]) {
      res = Math.max(res, dfs(idx + 1, jdx + 1, dp, s, p, set) + 1);
    }

    if (!set.contains(idx)) {
      res = Math.max(res, dfs(idx + 1, jdx, dp, s, p, set));
    }
    return dp[idx][jdx] = res;
  }

}