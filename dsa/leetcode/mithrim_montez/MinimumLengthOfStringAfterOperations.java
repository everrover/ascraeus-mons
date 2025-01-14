package dsa.leetcode.mithrim_montez;

public class MinimumLengthOfStringAfterOperations {

  /**
   * https://leetcode.com/problems/minimum-length-of-string-after-operations/description/?envType=daily-question&envId=2025-01-13
   *
   * To achieve the minimal length, the key is recognizing that the frequency of each character matters. 
   * You can repeatedly remove pairs of characters if they appear more than twice
   * Hence, the minimum length is determined by processing until characters are reduced to at most two occurrences
   * FOR odd frequency characters, we can remove evrrything except center character, y y y x y y y
   * FOR even frequency characters, we can remove everything except 2 central characters, y y y x x y y y
   *
   * TC: O(n), SC: O(1)
   * #string-manipulation #greedy #hash-table #medium
   */

  public int minimumLength(String s) {
    int cnt[] = new int[26]; // Array to store frequency of each character.
    int res = s.length();
    for (char c : s.toCharArray()) {
      cnt[c - 'a']++; // Counting occurrences of each character.
    }
    for (int c : cnt) {
      if (c <= 2) continue; // If frequency is less than 3, skip.
      res -= c - (c % 2 == 0 ? 2 : 1); // Adjust result based on character frequency.
    }
    return res;
  }
}