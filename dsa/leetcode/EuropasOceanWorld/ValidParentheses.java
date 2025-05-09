package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class ValidParentheses {
  /**
   * https://leetcode.com/problems/valid-parentheses/
   * 
   * To determine if a string containing only parentheses characters is valid, we use a stack data structure. 
   * The idea is to iterate through each character in the string; if it is an opening bracket, we push it 
   * onto the stack. Whenever a closing bracket is encountered, we check the top of the stack to see if it 
   * matches the type of the closing bracket. If it does, we pop the stack and proceed; otherwise, the string 
   * is invalid. If the stack is empty by the end of the string traversal, then the string was valid.
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