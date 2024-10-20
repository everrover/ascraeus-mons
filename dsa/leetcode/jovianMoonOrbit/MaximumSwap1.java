package dsa.leetcode.jovianMoonOrbit;

public class MaximumSwap {
  /**
   * https://leetcode.com/problems/maximum-swap/
   *
   * Swap two digits at most once to get the maximum valued number.
   * Iterate over the digits, for each, find the largest digit to the right that is greater, and swap it.
   * 
   * TC: O(n) SC: O(1)
   * #math #greedy #medium
   */

  public int maximumSwap(int num) {
    char[] digits = Integer.toString(num).toCharArray();
    int[] buckets = new int[10]; // Position of last occurrence of each digit
    // Fill the bucket with the last position of each digit
    for (int i = 0; i < digits.length; i++) {
      buckets[digits[i] - '0'] = i;
    }
    // Try to swap each digit with the largest possible digit to its right
    for (int i = 0; i < digits.length; i++) {
      for (int k = 9; k > digits[i] - '0'; k--) {
        if (buckets[k] > i) { // A larger digit exists to the right
          char tmp = digits[i];
          digits[i] = digits[buckets[k]];
          digits[buckets[k]] = tmp;
          return Integer.valueOf(new String(digits));
        }
      }
    }
    return num;
  }
}