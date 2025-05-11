package dsa.leetcode.EuropasOceanWorld;

import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
  /**
   * https://leetcode.com/problems/generate-parentheses/description/
   *
   * Generate all combinations of well-formed parentheses using backtracking.
   * At each recursion level, i decide to add an valid parenthesis set, either within a set of parentheses and/or
   * outside of it.
   *
   * TC: O(4^n / sqrt(n)), SC: O(4^n / sqrt(n))
   * #string #dynamic-programming #backtracking #medium
   */

  public List<String> generateParenthesis(int n) {
        List<String> list = new LinkedList<>();
        if(n == 0){
            list.add("");
            return list;
        }
        List<String> tmp, tmp2;
        for(int i=0; i<n; i++){
            int m = n-1-i;
            tmp = generateParenthesis(i);
            tmp2 = generateParenthesis(m);
            for(String t: tmp){
                for(String t2: tmp2){
                    list.add('('+t2+')'+t);
                }
            }
        }
        return list;
    }
}