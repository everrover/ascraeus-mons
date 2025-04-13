package dsa.leetcode.JupitersGreatStorm;

public class CountGoodNumbers {

  /**
   * https://leetcode.com/problems/count-good-numbers/description/?envType=daily-question&envId=2025-04-13
   * 
   * ~ https://leetcode.com/problems/powx-n/description/
   *
   * A digit string is good if the digits at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
   * For every two pairs, we need to multiply the result by 20
   * Calculate the total number of good digit strings of length n, modulo 10^9 + 7.
   * Use fast exponentiation to compute the count efficiently. [This needs to be mugged up❗️]
   * 
   * Fast exponentiation is a method to compute large powers of numbers efficiently. But only
   * works for prime numbers and their powers at the base... `x` => Implication of Fermat's little theorm and Euler's theorm's
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

  private double pow(double x, long y){
    if(y < 0) {
      return 1.0 / pow(x, -y);
    }
    double ret = 1, mul = x;
    while (y > 0) {
      if (y % 2 == 1) ret = (ret * mul);
      mul = (mul * mul);
      y /= 2;
    }
    return ret;
  }
  public double myPow(double x, int n) {
    return pow(x,(long)n);
  }
}