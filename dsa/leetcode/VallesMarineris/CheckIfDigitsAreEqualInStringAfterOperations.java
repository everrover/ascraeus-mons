package dsa.leetcode.VallesMarineris;

class CheckIfDigitsAreEqualInStringAfterOperations {

  /**
   * https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/
   *
   * Do as is said...
   *
   * TC: O(n^2) SC: O(n)
   * #math #string #simulation #easy
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