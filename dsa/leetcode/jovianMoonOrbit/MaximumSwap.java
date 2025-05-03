package dsa.leetcode.JovianMoonOrbit;

import java.util.*;

public class MaximumSwap {

  /**
   * https://leetcode.com/problems/maximum-swap/
   *
   * For each number's last occurrence, we check if there is a larger number to the right of it. 
   * We pick the largest number to the right of it and swap it with the current number.
   *
   * TC: O(n^2) SC: O(1)
   * #math #greedy #medium
   */

  public int maximumSwap(int num) {
    char[] digits = Integer.toString(num).toCharArray();

    int[] buckets = new int[10];
    for (int i = 0; i < digits.length; i++) {
      buckets[digits[i] - '0'] = i;
    }

    for (int i = 0; i < digits.length; i++) {
      for (int k = 9; k > digits[i] - '0'; k--) {
        if (buckets[k] > i) {
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