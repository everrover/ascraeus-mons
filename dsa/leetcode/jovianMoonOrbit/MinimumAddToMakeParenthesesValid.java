package dsa.leetcode.JovianMoonOrbit;

import java.util.Stack;

public class MinimumAddToMakeParenthesesValid {
  
  /**
   * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
   *
   * Iterate over the string and use a stack to keep track of unmatched opening parentheses.
   * For each closing parenthesis, either pop from the stack if there's a matching opening parenthesis or increment a counter.
   * Finally, add the size of the stack to the counter to get the minimum number of parentheses to be added.
   * 
   * TC: O(n) SC: O(n)
   * #string #stack #greedy #medium
   */
  
  public int minAddToMakeValid(String s) {
    int res = 0, i = 0;
    Stack<Integer> st = new Stack<>();
    while (i < s.length()) {
      char c = s.charAt(i++);
      if (c == '(') {
        st.push(1);
      } else {
        if (st.isEmpty()) res++;
        else st.pop();
      }
    }
    res += st.size();
    return res;
  }
}