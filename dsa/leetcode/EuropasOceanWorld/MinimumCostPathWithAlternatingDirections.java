package dsa.leetcode.EuropasOceanWorld;

public class MinimumCostPathWithAlternatingDirections {
  /**
   * https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-i/description/
   *
   * This problem and solution is in-correct.
   *
   * Either of these paths can be taken:
   * > ^ > ^ > ^ > ^ ... > ^ v => due to > ^ i moved towards the right since nothing's above
   * v < v < v < ... v < > => same reason as above
   * Which allows us to traverse the grid of size 2xn or mx2 max...
   *
   * The solution below is based on observation that we can only move to 0,0 or 1,0 or 0,1
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

  private int worksforthecorrectcase(int m, int n) {
    if(m>2 || n>2 || m<=0 || n<=0) {
      // Impossible to reach the destination
      return -1;
    } else if(m==1 || n==1) {
      int r = Math.max(m, n);
      // Cost for single row or column
      return r * (r + 1) / 2;
    }

    int r = Math.max(m, n);
    return (r * (r - 1)) / 2 + r * 2;
  }
}