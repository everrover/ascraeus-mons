package dsa.leetcode.mithrim_montez;

public class ConstructKPalindromeStrings {

  /**
   * https://leetcode.com/problems/construct-k-palindrome-strings/description/?envType=daily-question&envId=2025-01-11
   *
   * ❗️ made in mistake in comprehension, it's not about number of possible palindromes but constructing exactly k palindromes
   * - length(s) < k, not enough characters to split into k parts => impossible
   * - length(s) == k, just enough characters to split into k parts => possible
   * - length(s) > k, enough characters, odd edge-case => possible if odd characters <= k
   * 
   * for third case, even numbered chars can be arranged with ease, but odd numbered chars have to be used
   * atleast once as a middle char once or as a "individual character palindrome"
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