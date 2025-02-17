package dsa.leetcode.VallesMarineris;

import java.util.*;

public class RemoveAllOccurrencesOfASubstring {

  /**
   * https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/
   *
   * The problem is to remove all occurrences of a given substring from a string. This can be done by iterating through the main string and removing the substring whenever it's found, until no more occurrences exist.
   *
   * TC: O(n * m) SC: O(n)
   * #string #stack #simulation #medium
   */

   public String removeOccurrences(String s, String part) {
    char []schs = s.toCharArray();
    char []pchs = part.toCharArray();
    StringBuilder res = new StringBuilder();
    Stack<Character> st = new Stack<>();
    for(int i=0; i<schs.length; i++){
      st.push(schs[i]);
      if(st.size() >= pchs.length && checkplz(pchs, st)){
        int j = pchs.length;
        while(j-->0) st.pop();
      }
    }
    while(!st.isEmpty()){
      res.append(st.pop());
    }
    return res.reverse().toString();
  }

  private boolean checkplz(char []pchs, Stack<Character> st){
    Stack<Character> temp = new Stack<>();
    boolean res = true;
    for(int idx = pchs.length-1; idx>=0; idx--){
      char tmp = st.pop();
      temp.push(tmp);
      if(tmp != pchs[idx]){
        res = false;
        break;
      }
    }
    while(!temp.isEmpty()) st.push(temp.pop());
    return res;
  }

}