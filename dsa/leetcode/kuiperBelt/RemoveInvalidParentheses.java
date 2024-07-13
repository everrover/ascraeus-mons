package dsa.leetcode.kuiperBelt;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * This solution utilizes recursion and backtracking to effectively remove invalid parentheses,
 * ensuring the generation of valid parentheses combinations with the minimum number of removals.
 * 
 * TC: O((2^n) * n) SC: O(n) due to the recursion stack and the space for storing the answer
 * #backtracking #string #bfs #hard
 */

class Solution {
  public List<String> removeInvalidParentheses(String s) {
    List<String> ans = new ArrayList<>();
    remove(s, ans, 0, 0, new char[]{'(', ')'});
    return ans;
  }

  public void remove(String s, List<String> ans, int li, int lj,  char[] par) {
    for (int stack = 0, i = li; i < s.length(); ++i) {
      if (s.charAt(i) == par[0]) stack++;
      if (s.charAt(i) == par[1]) stack--;
      if (stack >= 0) continue;
      for (int j = lj; j <= i; ++j)
        if (s.charAt(j) == par[1] && (j == lj || s.charAt(j - 1) != par[1]))
          remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
      return;
    }
    String reversed = new StringBuilder(s).reverse().toString();
    if (par[0] == '(') // finished left to right
      remove(reversed, ans, 0, 0, new char[]{')', '('});
    else // finished right to left
      ans.add(reversed);
  }
  
}