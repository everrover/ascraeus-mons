package dsa.leetcode.mithrim_montez;

public class FindValidPairOfAdjacentDigitsInString {

  /**
   * https://leetcode.com/problems/find-valid-pair-of-adjacent-digits-in-string/description/
   *
   * Use a frequency array to count occurrences of each digit. Traverse the string to identify
   * a valid pair by checking the frequency of adjacent different digits against their value.
   *
   * TC: O(n) SC: O(1)
   * #string #hashmap #easy
   */

  public String findValidPair(String s) {
    int[] cnts = new int[10];
    for (char ch : s.toCharArray()) {
      cnts[ch - '0']++;
    }
    for (int i = 1; i < s.length(); i++) {
      int num1 = s.charAt(i - 1) - '0', num2 = s.charAt(i) - '0';
      if (num1 == num2) continue;
      if (cnts[num1] == num1 && cnts[num2] == num2) return num1 + "" + num2;
    }
    return "";
  }
}