package dsa.leetcode.VallesMarineris;

class Solution {

  /**
   * https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/
   *
   * For each pair of consecutive digits in the string, calculate the sum of the
   * two digits modulo 10. Continuously perform this operation until the string
   * has only two digits left. If the final two digits are equal, return true;
   * otherwise, return false.
   *
   * TC: O(n^2) SC: O(n)
   * #math #string #simulation #easydifficulty
   */

  public boolean hasSameDigits(String s) {
    // Edge case
    if (s.length() < 2) return false;
    
    while(s.length() > 2) {
      StringBuilder tmp = new StringBuilder();
      char[] chs = s.toCharArray();
      for(int i = 1; i < chs.length; i++) {
        tmp.append(((int)(chs[i]-'0') + (int)(chs[i-1]-'0')) % 10);
      }
      s = tmp.toString();
    }
    
    return s.charAt(0) == s.charAt(1);
  }
}