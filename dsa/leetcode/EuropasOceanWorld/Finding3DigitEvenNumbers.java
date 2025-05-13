package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class Finding3DigitEvenNumbers {
  /**
   * https://leetcode.com/problems/finding-3-digit-even-numbers/description/?envType=daily-question&envId=2025-05-12
   *
   * Iterate through all 3-digit even numbers. For each number, count the occurrences
   * of each digit and check against the digits array. Collect all numbers that
   * can be formed with the given digits.
   *
   * TC: O(n + k), SC: O(1)
   * #array #hash-table #sorting #enumeration #easy
   */
  public int[] findEvenNumbers(int[] digits) {
    List<Integer> result = new ArrayList<>();
    int[] count = new int[10];
    for (int digit : digits) {
      count[digit]++;
    }
    for (int num = 100; num < 1000; num += 2) {
      int hundreds = num / 100;
      int tens = (num / 10) % 10;
      int units = num % 10;
      int[] numCount = count.clone();
      if (numCount[hundreds]-- > 0 && numCount[tens]-- > 0 && numCount[units]-- > 0) {
        result.add(num);
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }
}