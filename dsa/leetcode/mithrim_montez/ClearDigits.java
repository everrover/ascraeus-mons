package dsa.leetcode.mithrim_montez;

public class ClearDigits {
  /**
   * https://leetcode.com/problems/clear-digits/description/?envType=daily-question&envId=2025-02-10
   *
   * Process the string by iterating from left to right, deleting the first digit and the closest non-digit character to its left until no digits remain.
   * 
   * TC: O(n) SC: O(n)
   * #string #stack #simulation #easy
   */

  public String removeDigits(String s) {
    StringBuilder sb = new StringBuilder();
    int idx = 0, N = s.length();
    while (idx < N) {
      char ch = s.charAt(idx++);
      if ('0' <= ch && ch <= '9') {
        // If it's a digit, delete the nearest left non-digit character
        if (sb.length() > 0) {
          sb.delete(sb.length() - 1, sb.length());
        }
      } else {
        sb.append(ch); // Append non-digit characters
      }
    }
    return sb.toString();
  }
}