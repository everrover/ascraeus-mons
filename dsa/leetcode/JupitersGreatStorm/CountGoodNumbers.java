package dsa.leetcode.JupitersGreatStorm;

public class CountGoodNumbers {

  /**
   * https://leetcode.com/problems/count-good-numbers/description/?envType=daily-question&envId=2025-04-13
   *
   * A digit string is good if the digits at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
   * Calculate the total number of good digit strings of length n, modulo 10^9 + 7.
   * Use fast exponentiation to compute the count efficiently.
   *
   * TC: O(log n) SC: O(1)
   * #math #recursion #medium
   */

  private final static int M = (int) 1e9+7;

  public int countGoodNumbers(long n) {
    if (n == 1) return 5;
    // Calculate number of good numbers
    long evenPos = n / 2 + n % 2;  // number of even positions
    long oddPos = n / 2;           // number of odd positions
    return (int)((quickmul(5, evenPos) * quickmul(4, oddPos)) % M);
  }

  private long quickmul(long x, long y) {
    long ret = 1;
    long mul = x;
    while (y > 0) {
      if (y % 2 == 1) ret = (ret * mul) % M;
      mul = (mul * mul) % M;
      y /= 2;
    }
    return ret;
  }
}