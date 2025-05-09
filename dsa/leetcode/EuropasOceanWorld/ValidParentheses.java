package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class ValidParentheses {
  /**
   * https://leetcode.com/problems/valid-parentheses/
   * 
   * Uses stack to check if a comlentry valid parentheses is present or not.
   * 
   * TC: O(n) SC: O(n)
   * #string #stack #parentheses #easy
   */
  public boolean isValid(String s) {
    char[] str = s.toCharArray();
    List<Character> stack = new ArrayList<>();
    for(char ch: str){
      if(ch == '{' || ch == '(' || ch == '['){
        stack.add(ch); // Add opening brackets to the stack
      }else{
        if(stack.isEmpty()) return false; // No matching opening bracket
        // Check matching pairs
        if(ch == '}' && '{' == stack.get(stack.size()-1)){
          stack.remove(stack.size()-1);
        }else if(ch == ')' && '(' == stack.get(stack.size()-1)){
          stack.remove(stack.size()-1);
        }else if(ch == ']' && '[' == stack.get(stack.size()-1)){
          stack.remove(stack.size()-1);
        }else{
          return false; // Mismatch found
        }
      }
    }
    return stack.isEmpty(); // True if all brackets matched
  }
}