package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class CustomSortString {

  /**
   * https://leetcode.com/problems/custom-sort-string/
   *
   * Permute the characters of s so that they match the custom order defined in order.
   * 
   * Count how many times each character appears in s and arrange characters by the order.
   * 
   * Append remaining characters that are not in order at the end. Or on positions where they appear in s.
   *
   * TC: O(n) SC: O(n)[res arr]
   * #hash-table #string #sorting #medium
   */

  public String customSortString(String order, String s) {
    char []res = new char[s.length()];
    Map<Character, Integer> map = new HashMap<>();
    for(char ch: order.toCharArray()){
      map.putIfAbsent(ch, 0);
    }
    for(int i=0; i<s.length(); i++){
      char ch = s.charAt(i);
      if(map.containsKey(ch)) {
        map.put(ch, map.get(ch)+1);
      }
    }
    for(int i=0, j=0; i<s.length(); i++){
      char ch = s.charAt(i);
      if(j<order.length() && map.containsKey(ch)){
        char o = order.charAt(j);
        res[i] = o;
        map.put(o, map.get(o)-1);
        if(map.get(o) == 0) ++j;
      }else{
        res[i] = ch;
      }
    }
    return new String(res);
  }
}