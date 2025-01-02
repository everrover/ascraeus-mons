package dsa.leetcode.fermi_s_paradox;

public class LongestPalindromicSubstring {
  /**
   * https://leetcode.com/problems/longest-palindromic-substring/description/
   *
   * Expand around the center for each character and find the longest palindromic substring.
   * For each character, compare characters on both sides and expand outwards while they match.
   *
   * TC: O(n^2) SC: O(1)
   * #two-pointers #dynamic-programming #string #medium
   */

  public String longestPalindrome(String s) {
    char[] chs = s.toCharArray();
    int ml = 0, i = 0;
    String sol = "";
    final int m = chs.length;

    while (i < m) {
      int j = i, k = i;
      // expansion around center
      while (j >= 0 && k < m && chs[j] == chs[k]) {
        if ((k - j + 1) > ml) {
          ml = k - j + 1;
          sol = s.substring(j, k + 1);
        }
        j--; k++;
      }

      j = i; k = i + 1;
      while (j >= 0 && k < m && chs[j] == chs[k]) {
        if ((k - j + 1) > ml) {
          ml = k - j + 1;
          sol = s.substring(j, k + 1);
        }
        j--; k++;
      }
      i++;
    }
    return sol;
  }
}