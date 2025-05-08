package dsa.leetcode.EuropasOceanWorld;

public class ReverseInteger {
  
  /**
   * https://leetcode.com/problems/reverse-integer/
   *
   * Reverses the digits of a 32-bit signed integer. Checks if reversal
   * exceeds 32-bit signed integer range and returns 0 in such a case.
   * 
   * TC: O(log(x)) SC: O(log(x)), where x is the input integer
   * #math #integer #easy
   */

  class Solution {
    public int reverse(int x) {
      StringBuilder inte = new StringBuilder((x > 0 ? x : -x) + "");
      return (x > 0 ? 1 : -1) * Integer.parseInt(inte.reverse().toString());
    }
  }
}