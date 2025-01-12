package dsa.leetcode.mithrim_montez;

public class ConstructKPalindromeStrings {

  /**
   * https://leetcode.com/problems/construct-k-palindrome-strings/description/?envType=daily-question&envId=2025-01-11
   *
   * Given a string s and an integer k, to determine if it's possible to split s into k palindromes:
   * - If the length of s is less than k, then it's impossible to form k palindrome strings.
   * - If the number of characters with an odd count is greater than k, it means constructing k palindromes is impossible.
   * - Otherwise, it is possible to form k palindromes.
   * 
   * TC: O(n) SC: O(1)
   * #hash-table #string #greedy #counting #medium
   */

  public boolean canConstruct(String s, int k) {
    if (s.length() < k) return false;  // Not enough characters to split into k parts
    if (s.length() == k) return true; // Each character can be its own palindrome

    int odd = 0;
    int[] chs = new int[26];
    for (char ch: s.toCharArray()) {
      chs[ch - 'a']++;
    }
    for (int c: chs) {
      odd += (c % 2 == 0 ? 0 : 1);
    }

    return odd <= k; // If odd letter count <= k, it's possible to create k palindromes
  }
}