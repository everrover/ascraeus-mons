package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class SplitArrayIntoFibonacciSequence {
  
  /**
   * https://leetcode.com/problems/split-array-into-fibonacci-sequence/
   *
   * Simple brute-force. 
   * Edge cases:
   * 1. No leading zeros except for zero itself
   * 2. No overflow ? >= Integer.MAX_VALUE
   * 3. No negative numbers
   * 
   * Ditto : https://leetcode.com/problems/additive-number/
   *
   * TC: O(2^n) SC: O(n)
   * #string #backtracking #medium
   */
  
  private boolean recurse(int idx, String numfull, int prev, int prev1, int prev2, final List<Integer> res) {
    if(idx == numfull.length()) {
      if(res.size() < 3) return false;
      else if(prev2 != prev1 + prev) return false;
      else return true;
    }
    for(int i = idx + 1; i <= numfull.length() && i <= idx + 10; i++) { 
      if(numfull.charAt(idx) == '0' && i - idx >= 2) break; // No leading zeros except for zero itself
      long curr = Long.valueOf(numfull.substring(idx, i));
      if(curr > Integer.MAX_VALUE) break;
      if(prev2 != -1 && prev1 != -1 && curr != prev2 + prev1) continue;
      res.add((int)curr);
      boolean rec = recurse(i, numfull, prev1, prev2, (int)curr, res);
      if(rec) { return true; }
      res.remove(res.size() - 1);
    }
    return false;
  }

  public List<Integer> splitIntoFibonacci(String num) {
    List<Integer> res = new ArrayList<>();
    recurse(0, num, -1, -1, -1, res);
    if(res.size() < 3) res.clear();
    return res;
  }
}