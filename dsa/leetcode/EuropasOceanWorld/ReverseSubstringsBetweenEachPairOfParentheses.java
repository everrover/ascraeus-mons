package dsa.leetcode.EuropasOceanWorld;

import java.util.Stack;

public class ReverseSubstringsBetweenEachPairOfParentheses {

  /**
   * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/description/?envType=company&envId=agoda&favoriteSlug=agoda-all
   *
   * To solve this problem, utilize a stack to handle nested parentheses. Iterate through the string, and for each '(', push the current string builder to stack and start a new one. When encountering ')', reverse the current string builder, pop from the stack to append the reversed string, and continue. Remove all brackets to achieve the desired output.
   * 
   * TC: O(n) SC: O(n)
   * #string #stack #medium
   */

    public String reverseParentheses(String s) {
      Stack<StringBuilder> stack = new Stack<>();
      StringBuilder current = new StringBuilder();

      for (char ch : s.toCharArray()) {
        if (ch == '(') {
          stack.push(current);
          current = new StringBuilder();
        } else if (ch == ')') {
          StringBuilder reversed = current.reverse();
          current = stack.pop().append(reversed);
        } else {
          current.append(ch);
        }
      }

      return current.toString();
    }
}