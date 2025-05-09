package dsa.leetcode.EuropasOceanWorld;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  /**
   * https://leetcode.com/problems/generate-parentheses/description/
   *
   * Generate all combinations of well-formed parentheses using backtracking.
   * At each recursion level, decide to add an opening or closing parenthesis,
   * ensuring that no invalid combinations are formed.
   *
   * TC: O(4^n / sqrt(n)), SC: O(4^n / sqrt(n))
   * #string #dynamic-programming #backtracking #medium
   */

  public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    backtrack(list, new StringBuilder(), 0, 0, n);
    return list;
  }

  private void backtrack(List<String> list, StringBuilder sb, int open, int close, int max) {
    // Base case: when the current string is well-formed
    if (sb.length() == max * 2) {
      list.add(sb.toString());
      return;
    }

    // Add an open parenthesis if possible
    if (open < max) {
      sb.append('(');
      backtrack(list, sb, open + 1, close, max);
      sb.deleteCharAt(sb.length() - 1);
    }

    // Add a close parenthesis if it doesn't exceed open
    if (close < open) {
      sb.append(')');
      backtrack(list, sb, open, close + 1, max);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}