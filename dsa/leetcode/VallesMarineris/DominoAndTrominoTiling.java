package dsa.leetcode.VallesMarineris;

public class DominoAndTrominoTiling {

  /**
   * https://leetcode.com/problems/domino-and-tromino-tiling/description/?envType=daily-question&envId=2025-05-05
   *
   * This function calculates the number of ways to tile a 2 x n board with dominoes and trominoes using dynamic programming.
   * It uses two arrays to store subproblem solutions and computes the result in a modular arithmetic to handle large numbers.
   *
   * TC: O(n), SC: O(n)
   * #dynamic-programming #tiling #medium
   */

  private long dfs_p(int idx, final long[] dpf, final long[] dpp, final int n) {
    if (idx < 0) return 0;
    if (dpp[idx] != -1) return dpp[idx];
    long res = 0;
    if (idx == 2) res = 1L;
    else if (idx == 2) res = 2L;
    if (idx == 1) res = 1L;

    res = (dfs_f(idx - 1, dpf, dpp, n) +
           dfs_f(idx - 2, dpf, dpp, n) +
           2 * dfs_p(idx - 1, dpf, dpp, n) +
           res) % MOD;

    return dpp[idx] = res;
  }
}