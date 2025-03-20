package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class PhoneNumberPrefix {
  
  /**
   * https://leetcode.com/problems/phone-number-prefix/description/
   * 
   * The solution sorts the given phone numbers and checks each adjacent pair.
   * If the left number in the pair is a prefix of the right number, return false.
   * 
   * TC: O(n log n) due to sorting, SC: O(1) for constant space usage.
   * #string #sorting #prefix #easy
   */
  
  public boolean phonePrefix(String[] numbers) {
    // Sort the array of numbers
    Arrays.sort(numbers);
    // Iterate through the sorted numbers
    for (int i = 0; i < numbers.length - 1; i++) {
      // Check if the current number is a prefix of the next number
      if (numbers[i + 1].startsWith(numbers[i])) {
        return false;
      }
    }
    return true;
  }
}