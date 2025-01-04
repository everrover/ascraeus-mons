package dsa.leetcode.fermi_s_paradox;

public class SubstringMatchingPattern {

  /**
   * https://leetcode.com/problems/substring-matching-pattern/description/
   *
   * Divide the pattern in two strings around '*' and search each part in the string.
   * Check if both parts can be found sequentially in the string.
   *
   * TC: O(n*m) SC: O(1)
   * #string #pattern-matching #substring #easy
   */

  public boolean isPatternSubstring(String s, String p) {
    int idx = p.indexOf('*');
    // Splitting pattern into two parts
    String p1 = p.substring(0, idx);
    String p2 = p.substring(idx + 1);

    int e1 = check(s, p1);
    if (e1 == -1) return false;

    int e2 = check(s.substring(e1), p2);
    return e2 != -1;
  }

  private int check(String s, String k) {
    int i = s.indexOf(k);
    return i == -1 ? -1 : (i + k.length());
  }

}