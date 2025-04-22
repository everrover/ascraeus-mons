package dsa.leetcode.JupitersGreatStorm;

class Solution {
  private static long[][] pascal;
  static private final int MOD = (int)1e9+7;

  /**
   * https://leetcode.com/problems/count-the-number-of-ideal-arrays/description/?envType=daily-question&envId=2025-04-22
   *
   * To calculate the number of ideal arrays, a dynamic programming approach is used
   * in combination with combinatorial mathematics. By leveraging Pascal's Triangle,
   * the algorithm efficiently finds the number of distinct arrays where each element
   * is divisible by the previous and bounded by `maxValue`.
   *
   * TC: O(n * maxValue) SC: O(n * k) where k is the limit in binomial coefficients.
   */

  private void buildPascals(int n, int k) {
    pascal = new long[n+1][k+1];
    for (int i = 0; i <= n; i++) {
      pascal[i][0] = 1;
      int MIN = Math.min(i, k);
      for (int j = 1; j <= MIN; j++) {
        pascal[i][j] = (pascal[i-1][j-1] + pascal[i-1][j]) % MOD;
      }
    }
  }

  private long nCk(int m, int n) {
    return pascal[m][n];
  }
}