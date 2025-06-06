package dsa.leetcode.EuropasOceanWorld;

import java.util.Stack;

public class UsingARobotToPrintTheLexicographicallySmallestString {

  /**
   * https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/description/?envType=daily-question&envId=2025-06-06
   * 
   * The problem requires using a robot to print the lexicographically smallest string from given string 's'.
   * Maintain a count array for each character in 's'. A stack is used to manage the string t.
   * The goal is to push characters to 't' using a stack until the next smallest character can be appended to the result.
   * 
   * TC: O(n) SC: O(n)
   * #string #stack #greedy #medium
   */

  public String robotWithString(String s) {
    int []cnts = new int[26];
    for (char ch: s.toCharArray()) {
      cnts[ch - 'a']++;
    }

    StringBuilder res = new StringBuilder();
    char mc = 'a';
    Stack<Character> st = new Stack<>();

    for (char ch: s.toCharArray()) {
      st.push(ch);
      cnts[ch - 'a']--;
      while(mc != 'z' && cnts[mc - 'a'] == 0) mc++;
      while(!st.isEmpty() && st.peek() <= mc) {
        res.append(st.pop());
      }
    }
    return res.toString();
  }
}