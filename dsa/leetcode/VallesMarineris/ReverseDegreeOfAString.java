package dsa.leetcode.VallesMarineris;

public class ReverseDegreeOfAString {

  /**
   * https://leetcode.com/problems/reverse-degree-of-a-string/description/
   * 
   * The reverse degree is calculated by multiplying each character's position in 
   * the reversed alphabet with its position in the string (1-indexed).
   * Sum these products to obtain the reverse degree.
   * 
   * TC: O(n) SC: O(1)
   * #string #easy
   */
  
  public int reverseDegree(String s) {
    int res = 0, idx = 1;
    for(char ch : s.toCharArray()) {
      res += (26 - (ch - 'a')) * (idx++);
    }
    return res;
  }
}