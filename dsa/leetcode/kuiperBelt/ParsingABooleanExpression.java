package dsa.leetcode.KuiperBelt;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ParsingABooleanExpression {
  /**
   * https://leetcode.com/problems/parsing-a-boolean-expression/
   *
   * Given a boolean expression string, evaluates using stack. For each closing bracket, evaluates current subexpression.
   * Keeps track of characters to apply the respective operator (& for AND, | for OR, ! for NOT) on the collected characters.
   * 
   * TC: O(n), SC: O(n)
   * #stack #recursion #string #hard
   */
  public boolean parseBoolExpr(String expression) {
    Stack<Character> stk = new Stack<>();
    for (int i = 0; i < expression.length(); ++i) {
      char c = expression.charAt(i);
      if (c == ')') {
        Set<Character> seen = new HashSet<>();
        while (stk.peek() != '(') seen.add(stk.pop());
        stk.pop();
        char operator = stk.pop();
        if (operator == '&') {
          stk.push(seen.contains('f') ? 'f' : 't');
        } else if (operator == '|') {
          stk.push(seen.contains('t') ? 't' : 'f');
        } else { // ! expression.
          stk.push(seen.contains('t') ? 'f' : 't');
        }
      } else if (c != ',') {
        stk.push(c);
      }
    }
    return stk.pop() == 't';
  }
}