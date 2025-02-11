package dsa.leetcode.VallesMarineris;

import java.util.*;

public class RemoveAllOccurrencesOfASubstring {

  /**
   * https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/?envType=daily-question&envId=2025-02-11
   *
   * The problem is to remove all occurrences of a given substring from a string. This can be done by iterating through the main string and removing the substring whenever it's found, until no more occurrences exist.
   *
   * TC: O(n * m) SC: O(n)
   * #string #stack #simulation #medium
   */

  private boolean checkplz(char[] pchs, Stack<Character> st) {
    Stack<Character> temp = new Stack<>();
    for(int idx = pchs.length - 1; idx >= 0; idx--){
      boolean res = true;
      while(!temp.isEmpty()) st.push(temp.pop());
      char tmp = st.pop();
      temp.push(tmp);
      if(tmp != pchs[idx]){
        res = false;
        break;
      }
    }
    return res.reverse().toString();
  }

}