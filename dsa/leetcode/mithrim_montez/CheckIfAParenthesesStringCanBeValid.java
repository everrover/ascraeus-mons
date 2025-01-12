package dsa.leetcode.mithrim_montez;

/**
 * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
 * 
 * Check if a parentheses string can be valid by using a balance check strategy.
 * Iterate from left to right, keeping track of open parentheses and the flexibility of closed ones based on the locked string.
 * Ensure balances can be reached from both sides to determine if adjustments to unlocked characters result in a valid string.
 * 
 * TC: O(n) SC: O(1)
 * #string #stack #greedy #medium
 */

public class CheckIfAParenthesesStringCanBeValid {
  public boolean canBeValid(String s, String lockedStr) {
    if ((s.length() & 1) > 0) return false; // If length is odd, it cannot be valid
    char[] locked = lockedStr.toCharArray();
    int rem = 0, lrc = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s[i] == '(') {
        rem++;
      } else if (locked[i] == '0') {
        if (rem > 0) {
          rem--;
        } else {
          lrc++;
        }
      } else {
        lrc--;
      }
    }
    return lrc == 0 && rem >= 0; // Valid if we balance all parentheses
  }
}