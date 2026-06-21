package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * Uses a StringBuilder as an implicit stack. For each character, append it; if the last two characters are now equal, delete both — simulating a stack push and pop in a single pass. The invariant is that the StringBuilder never contains two consecutive equal characters at any point, so one linear scan suffices. TC: O(n), SC: O(n).
 *
 * TC: O(n) SC: O(n)
 * #string #stack #easy
 */

class RemoveAllAdjacentDuplicatesInString {
  public String removeDuplicates(String s) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<s.length(); i++){
      sb.append(s.charAt(i));
      int l = sb.length();
      if(l >= 2 && sb.charAt(l-1) == sb.charAt(l-2)){
        sb.deleteCharAt(l-1);
        sb.deleteCharAt(l-2);
      }
    }
    return sb.toString();
  }
}
