package dsa.leetcode.Ipestus;

public class FindNUniqueIntegersSumUpToZero {

  /**
   * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/description/
   *
   * Generate an array of n unique integers that are symmetric around 0.
   * For every positive integer, include its negative to maintain sum of zero.
   * If n is odd, include 0.
   *
   * TC: O(n) SC: O(n)
   * #array #math #easy
   */

  public int[] sumZero(int n) {
    int[] res = new int[n];
    for(int i = 0; i < n / 2; i++) {
      res[i] = -(i + 1);
      res[n - i - 1] = (i + 1);
    }
    if(n % 2 == 1) {
      res[n / 2] = 0;
    }
    return res;
  }
}