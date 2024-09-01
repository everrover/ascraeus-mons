package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class PalindromicSubstrings {

  /**
   * https://leetcode.com/problems/palindromic-substrings/submissions/
   *
   * Count the number of palindromic substrings by expanding around each possible center of the palindrome.
   * Each character and each pair of characters are considered as the center, and expand around them.
   * 
   * If DP were to be used, we'd have to traverse through (n^2)/2 states to check count of palindromic substrings.
   * dp(i, j) = s[i] == s[j] && dp(i + 1, j - 1), i>j
   *          = true, i==j
   *          = false, j>i
   * Increase the count of palindromic substrings if dp(i, j) is true for all (i, j) combos.
   * 
   * TC: O(n^2) SC: O(1)
   * #two-pointers #dynamic-programming #string #medium
   */

  int count = 0;

  public int countSubstrings(String s) {
    if (s == null || s.length() == 0) return 0;

    for (int i = 0; i < s.length(); i++) {
      extendPalindrome(s, i, i); // Consider odd length palindromes
      extendPalindrome(s, i, i + 1); // Consider even length palindromes
    }

    return count;
  }

  private void extendPalindrome(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      count++;
      left--;
      right++;
    }
  }
}