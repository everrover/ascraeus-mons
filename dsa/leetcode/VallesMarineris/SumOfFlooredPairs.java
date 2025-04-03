package dsa.leetcode.VallesMarineris;

public class SumOfFlooredPairs {

  /**
   * https://leetcode.com/problems/sum-of-floored-pairs/description/
   *
   * To solve this problem, calculate the frequency of occurrences for each element.
   * For each element, iterate through its multiples and use their frequencies
   * to compute the result efficiently.
   * 
   * TC: O(n log n) SC: O(n)
   * #array #math #prefix-sum #hard
   */

  private static final long MOD = 1_000_000_007;

  public int sumOfFlooredPairs(int[] nums) {
    int maxVal = 0;
    for (int num : nums) {
      maxVal = Math.max(maxVal, num);
    }

    long[] counts = new long[maxVal + 1];
    for (int num : nums) {
      counts[num]++;
    }

    long[] elems = new long[maxVal + 1];
    for (int i = 1; i <= maxVal; i++) {
      elems[i] = elems[i - 1] + counts[i];
    }

    long res = 0;
    for (int n = 1; n <= maxVal; n++) {
      if (counts[n] > 0) {
        for (long fact = maxVal / n; fact > 0; fact--) {
          long a = elems[(int) Math.min(maxVal, (fact + 1) * n - 1)] - elems[(int) fact * n - 1];
          res = (res + counts[n] * fact * a) % MOD;
        }
      }
    }

    return (int) res;
  }
}