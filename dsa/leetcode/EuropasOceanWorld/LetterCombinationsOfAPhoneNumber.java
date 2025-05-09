package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
  /**
   * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
   *
   * Simple back-tracking.
   *
   * TC: O(3^N * 4^M) SC: O(3^N * 4^M)
   * #hash-table #string #backtracking #medium
   */

   public List<String> letterCombinations(String digits) {
        List<String> ans = new LinkedList<>();
        char[][] letters = new char[][]{{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        recurse(letters, ans, new StringBuilder(""), digits.toCharArray(), 0);
        return ans;
    }
    public void recurse(char[][] letters, List<String> ans, StringBuilder sb, char[] chars, int pos){
        if(pos == chars.length) {if(pos != 0) ans.add(sb.toString()); return;}
        int num = chars[pos]-'0';
        // System.out.print(num+" ");
        for(char ch: letters[num]){
            sb.append(ch);
            recurse(letters, ans, sb, chars, pos+1);
            sb.deleteCharAt(pos);
        }
    }
}