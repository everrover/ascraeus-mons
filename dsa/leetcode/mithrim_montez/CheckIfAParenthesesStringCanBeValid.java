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
  public boolean canBeValid(String str, String lockedStr) {
      int lrc = 0, rlc = 0, rem = 0;
      char []s = str.toCharArray();
      char []locked = lockedStr.toCharArray();
      if((s.length&1) > 0) return false;
      for (int i = 0; i < s.length; i++){
        if (locked[i] == '0') {
          rem++;
        } else if (s[i] == '(') {
          lrc++;
        } else {
          if (rem > 0) {
            rem--;
          } else if (lrc > 0) {
            lrc--;
          } else {
            return false;
          }
        }
      }
      rem = 0;
      for (int i = s.length-1; i >=0; i--){
        if (locked[i] == '0') {
          rem++;
        } else if (s[i] == ')') {
          rlc++;
        } else {
          if (rem > 0) {
            rem--;
          } else if (rlc > 0) {
            rlc--;
          } else {
            return false;
          }
        }
      }
          
      return true;
  }
}