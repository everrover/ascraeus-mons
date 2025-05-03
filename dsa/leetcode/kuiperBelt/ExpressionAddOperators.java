package dsa.leetcode.KuiperBelt;

// imports here
import java.util.List;
import java.util.LinkedList;

public class ExpressionAddOperators {
  /**
   * https://leetcode.com/problems/expression-add-operators/
   *
   * Given a string num containing only digits and an integer target, return all possibilities to insert binary operators ('+', '-', '*') 
   * between the digits of num so that the resultant expression evaluates to the target value.
   * Operands in the returned expressions should not contain leading zeros.
   * 
   * TC: O(4^n) SC: O(4^n)
   * #math #string #backtracking #hard
   */
  class Solution {
    List<String> res = new LinkedList<>();
    long target;
    String num;

    public List<String> addOperators(String num, int target) {
      this.target = target;
      this.num = num;
      recurse(0, 0L, 0L, "");
      return res;
    }

    private void recurse(int idx, long prev, long value, String expr) {
      if (idx == num.length()) {
        if (value == target) res.add(expr);
        return;
      }

      for (int jdx = idx; jdx < num.length(); jdx++) {
        if (jdx > idx && num.charAt(idx) == '0') continue;
        long newval = Long.parseLong(num.substring(idx, jdx + 1));
        if (idx == 0) {
          recurse(jdx + 1, newval, newval, expr + newval);
        } else {
          recurse(jdx + 1, newval, value + newval, expr + "+" + newval);
          recurse(jdx + 1, -newval, value - newval, expr + "-" + newval);
          recurse(jdx + 1, prev * newval, value - prev + (newval * prev), expr + "*" + newval);
        }
      }
    }
  }
}