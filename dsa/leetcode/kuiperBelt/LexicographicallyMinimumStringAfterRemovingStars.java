package dsa.leetcode.KuiperBelt;

import java.util.Stack;

/**
 * https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/
 * The algorithm uses a stack for each character to track its indices.
 * Stars '*' are processed by removing the nearest left smallest character.
 * Resulting string is built by marking characters to retain and appending them in order.
 * 
 * Could;ve used min-heap as well.
 *
 * TC: O(n)
 * SC: O(n)
 * #hash-table #string #stack #greedy #heap-priority-queue #medium
 */
public class LexicographicallyMinimumStringAfterRemovingStars {

  public String clearStars(String s) {
    char []chs = s.toCharArray();
    StringBuilder result = new StringBuilder();
    Stack<Integer> []chars = new Stack[26];
    for(int i=0; i<chars.length; i++) chars[i] = new Stack<>();
    for(int i=0; i<chs.length; i++){
      if(chs[i] == '*') {
        int j=0;
        while(j<chars.length){
          if(!chars[j].isEmpty()) { chars[j].pop(); break; }
          j++;
        }
        if(j == 26) return "";
        continue;
      }
      chars[chs[i]-'a'].push(i);
    }
    boolean []mark = new boolean[s.length()];
    int j=0;
    while(j<chars.length){
      while(!chars[j].isEmpty()) { mark[chars[j].pop()] = true; }
      j++;
    }
    for(int i=0; i<s.length(); i++){
      if(mark[i]) result.append(s.charAt(i));
    }
    return result.toString();
  }
}
