package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class MaximumSwap {

  /**
   * https://leetcode.com/problems/maximum-swap/
   *
   * Given an integer num, this method finds the maximum valued number you can get
   * by swapping two digits at most once.
   *
   * TC: O(n) SC: O(n)
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