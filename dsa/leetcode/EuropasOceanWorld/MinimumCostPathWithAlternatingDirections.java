package dsa.leetcode.EuropasOceanWorld;

public class Solution {
  /**
   * https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-i/description/
   *
   * The solution uses the alternating directional move strategy defined in the problem.
   * The grid moves depend extensively on the dimensions specified: moves allowed on even or odd steps.
   * Special cases are handled when m > 2 or n > 2, where the answer is immediately -1.
   *
   * TC: O(1) SC: O(1)
   * #math #brainteaser #greedy #medium
   */
  public int minCost(int m, int n) {
    if (m > 2 || n > 2) {
      // Impossible to reach the destination
      return -1;
    } else if (m == 1 || n == 1) {
      int r = Math.max(m, n);
      // Cost for single row or column
      return r * (r + 1) / 2;
    } else if (m == 2 && n == 2) {
      // Impossible scenario for m = 2 and n = 2 under given rules
      return -1;
    }
    return -1;  // fallback for any unhandled cases
  }
}